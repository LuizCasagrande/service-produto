package com.luizcasagrande.serviceproduto.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.luizcasagrande.serviceproduto.http.data.request.ProdutoPersistDto;
import com.luizcasagrande.serviceproduto.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProdutoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Produto inserir(@RequestBody @Valid ProdutoPersistDto dto);

    @Operation(summary = "Retorna o produto correspondente ao identificador recuperado por parâmetro")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_10",
                                                "mensagem": "Produto com código 123 não encontrado",
                                                "documentacao": "http://localhost:8080/documentation"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("{id}")
    Produto buscar(@PathVariable("id") Long id);

    @PatchMapping("{id}")
    Produto alterar(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @PutMapping("{id}")
    Produto alterar(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable("id") Long id);
}
