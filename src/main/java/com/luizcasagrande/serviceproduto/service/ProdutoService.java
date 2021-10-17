package com.luizcasagrande.serviceproduto.service;

import com.luizcasagrande.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto inserir(Produto produto);

    Produto buscar(Long id);
}
