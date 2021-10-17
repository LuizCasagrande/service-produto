package com.luizcasagrande.serviceproduto.http.data.rerquest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoPersistDto {

    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
