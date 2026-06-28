package financeflow.model.entity;

import financeflow.enums.ComprovanteStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.UUID;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprovante")
@Getter
@Setter
@ToString (exclude = "dados")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComprovanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column (name = "data_envio_comprovante", updatable = false, nullable = false)
    private LocalDateTime dataEnvioComprovante;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column (name = "nome_arquivo", nullable = false)
    private String nomeArquivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComprovanteStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "tipo_arquivo", nullable = false)
    private String tipoArquivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] dados;

    @OneToOne
    @JoinColumn(name = "transacao_id")
    private TransacaoEntity transacao;

    @Column (name = "mensagem_erro")
    private String mensagemErro;
}
