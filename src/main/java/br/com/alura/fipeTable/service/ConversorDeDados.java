package br.com.alura.fipeTable.service;

import java.util.List;

public interface ConversorDeDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

}
