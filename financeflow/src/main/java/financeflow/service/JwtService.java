package financeflow.service;

import financeflow.model.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service

public class JwtService {
    @Value ("${jwt.secret}")
    private String secret;

    @Value ("${jwt.expiration-ms}")
    private Long expirationMs;


    public String generateToken(UsuarioEntity usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                //guarda o id e a role dentro do token
                .claim("id", usuario.getId())
                .claim("role", usuario.getRole().name())
                //momento que gerou o token
                .issuedAt(new Date())
                //momento que o token expira
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSignInKey())
                .compact();

    }

    public String extractUsername (String token) {

       return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        // diz qual claim extrair
        Claims claims = extractAllClaims(token);
        //retorna o valor daquela claim em especifica que foi passada no metodo
        return claimsResolver.apply(claims);

    }


    public boolean isTokenValid(String token, UserDetails userDetails) {


        String username = extractUsername(token);

        //email bate e o token nao expirou
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                //verifica se a data ja passou, se a expiracao for antes do momentoa atual, esta expirado
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
                //Vai ler e validar o token.
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                //decodifica, valida assinatura e expiracao e se algo estiver errado lanca excecao
                .parseSignedClaims(token)
                .getPayload();

    }

    private SecretKey getSignInKey() {
        //Transforma em bytes e especifica eles
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);

        // Gera a chave HMAC valida
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
