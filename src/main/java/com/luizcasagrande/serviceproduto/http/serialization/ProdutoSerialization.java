package com.luizcasagrande.serviceproduto.http.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.luizcasagrande.serviceproduto.http.data.response.ProdutoResponseDto;
import com.luizcasagrande.serviceproduto.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ProdutoSerialization extends JsonSerializer<Produto> {

    private final ModelMapper modelMapper;

    public ProdutoSerialization(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void serialize(Produto produto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ProdutoResponseDto produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);
        jsonGenerator.writeObject(produtoResponseDto);
    }
}
