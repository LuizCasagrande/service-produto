package com.luizcasagrande.serviceproduto.http;

import com.luizcasagrande.serviceproduto.http.data.request.ProdutoPersistDto;
import com.luizcasagrande.serviceproduto.model.Produto;
import com.luizcasagrande.serviceproduto.service.ProdutoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("produto")
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public Produto inserir(@RequestBody @Valid ProdutoPersistDto dto) {
        return produtoService.inserir(new Produto(dto.getDescricao(), dto.getValor()));
    }

    @Override
    public Produto buscar(@PathVariable("id") Long id) {
        return produtoService.buscar(id);
    }
}
