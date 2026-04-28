package financeflow.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name= "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_criacao" , nullable = false , updatable = false)
    private LocalDateTime dataCriacao;

}
