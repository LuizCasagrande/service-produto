package com.luizcasagrande.serviceproduto.repository;

import com.luizcasagrande.serviceproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
