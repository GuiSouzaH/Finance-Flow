package financeflow.dto;

import financeflow.enums.CategoriaTransacao;
import financeflow.enums.TipoTransacao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record TransacaoResponseDTO( UUID id,
                                    String descricao,
                                    BigDecimal valor,
                                    LocalDate dataTransacao,
                                    TipoTransacao tipoTransacao,
                                    CategoriaTransacao categoriaTransacao,
                                    LocalDateTime criadoEm
                                   ) {}
