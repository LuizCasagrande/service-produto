package com.luizcasagrande.serviceproduto.http.data.rerquest;

import java.math.BigDecimal;

public class ProdutoPersistDto {

    private String descricao;

    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
