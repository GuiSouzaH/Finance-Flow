package financeflow.model;


import financeflow.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@ToString(exclude = "senha") //nunca imprime a senha
@Getter @Setter
@EqualsAndHashCode(of = "id") // so compara pelo id
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name= "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_criacao" , nullable = false , updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
}
