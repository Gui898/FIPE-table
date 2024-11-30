package br.com.alura.fipeTable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Carro(@JsonAlias("Valor") String valor,
                    @JsonAlias("Marca") String marca,
                    @JsonAlias("Modelo") String modelo,
                    @JsonAlias("AnoModelo") Integer ano,
                    @JsonAlias("Combustivel") String combustivel){

    public String toString(){
        return "Valor: " + valor + " | Marca: " + marca + " | Modelo: " + modelo
        + " | Ano: " + ano + " | Combustível: " + combustivel;
    }
}
