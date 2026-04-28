package financeflow.dto;

import financeflow.enums.CategoriaTransacao;
import financeflow.enums.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public record TransacaoRequestDTO(@NotBlank String descricao,
                                  @NotNull @Positive BigDecimal valor,
                                  @NotNull LocalDate dataTransacao,
                                  @NotNull TipoTransacao tipoTransacao,
                                  @NotNull CategoriaTransacao categoriaTransacao

                                  ) { }
