package financeflow.model.entity;

import financeflow.enums.CategoriaTransacao;
import financeflow.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
@ToString(exclude = "usuario")
@Getter  @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name= "id", updatable = false, nullable = false)
    private UUID id;

    @Column (nullable = false)
    private String descricao;

    @Column (nullable = false)
    private BigDecimal valor;

    @Column(name = "data_transacao", nullable = false)
    private LocalDate dataTransacao;

    @CreationTimestamp
    @Column(name = "data_criacao",  updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_transacao", nullable = false)
    private TipoTransacao tipoTransacao;

    @Enumerated(EnumType.STRING)
    @Column(name="categoria_transacao", nullable = false)
    private CategoriaTransacao categoriaTransacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id" , nullable = false)
    private UsuarioEntity usuario;




}
