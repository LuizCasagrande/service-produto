package com.luizcasagrande.serviceproduto.service;

import com.luizcasagrande.serviceproduto.event.ProdutoPersistEvent;
import com.luizcasagrande.serviceproduto.model.Produto;
import com.luizcasagrande.serviceproduto.repository.ProdutoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ApplicationEventPublisher aplApplicationEventPublisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher aplApplicationEventPublisher) {
        this.produtoRepository = produtoRepository;
        this.aplApplicationEventPublisher = aplApplicationEventPublisher;
    }

    @Override
    public Produto salvar(Produto produto) {
        Produto produtoPersistido = produtoRepository.save(produto);
        aplApplicationEventPublisher.publishEvent(new ProdutoPersistEvent(this, produtoPersistido));
        return produtoPersistido;
    }

    @Override
    public Produto buscar(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> getNoResultException(id));
    }

    @Override
    public Produto alterar(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw getNoResultException(produto.getId());
        }
        return salvar(produto);
    }

    @Override
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw getNoResultException(id);
        }
        produtoRepository.deleteById(id);
    }

    private NoResultException getNoResultException(Long id) {
        return new NoResultException(String.format("Produto com código %d não encontrado", id));
    }
}
