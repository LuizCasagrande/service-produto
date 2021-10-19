package com.luizcasagrande.serviceproduto.service;

import com.luizcasagrande.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto salvar(Produto produto);

    Produto buscar(Long id);

    Produto alterar(Produto produto);

    void excluir(Long id);
}
