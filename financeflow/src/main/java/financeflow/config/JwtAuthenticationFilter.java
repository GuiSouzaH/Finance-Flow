package financeflow.config;

import financeflow.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Pega o header
        String authorizationHeader = request.getHeader("Authorization");

        //Se não tiver header ou não começar com Bearer, deixa seguir
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Extrai o token (remove "Bearer ")
        String token = authorizationHeader.substring(7);

        //Extrai o email do token
        String username = jwtService.extractUsername(token);

        //So autentica se tiver username e ainda não tiver autenticação
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Busca o usuário
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Valida o token
            if (jwtService.isTokenValid(token, userDetails)) {

                //Cria o token de autenticação
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                //Adiciona detalhes da requisição HTTP ao token de autenticacao, como ip e sessao
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Coloca no contexto do Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        //Deixa a requisição seguir
        filterChain.doFilter(request, response);
    }
}
