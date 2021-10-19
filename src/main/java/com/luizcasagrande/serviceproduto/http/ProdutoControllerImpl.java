package com.luizcasagrande.serviceproduto.http;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
        return produtoService.salvar(new Produto(dto.getDescricao(), dto.getValor()));
    }

    @Override
    public Produto buscar(@PathVariable("id") Long id) {
        return produtoService.buscar(id);
    }

    @Override
    public Produto alterar(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Produto produto = produtoService.buscar(id);

        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

        JsonNode produtoJsonNode = objectMapper.convertValue(produto, JsonNode.class);
        JsonNode patchJsonNode = patch.apply(produtoJsonNode);
        Produto produtoPersistir = objectMapper.treeToValue(patchJsonNode, Produto.class);

        return produtoService.salvar(produtoPersistir);
    }

    @Override
    public Produto alterar(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto) {
        Produto produto = new Produto(id, dto.getDescricao(), dto.getValor());
        return produtoService.alterar(produto);
    }

    @Override
    public void excluir(@PathVariable("id") Long id) {
        produtoService.excluir(id);
    }
}
