package br.com.alura.fipeTable.principal;

import br.com.alura.fipeTable.model.Carro;
import br.com.alura.fipeTable.model.Modelos;
import br.com.alura.fipeTable.model.Veiculo;
import br.com.alura.fipeTable.service.ConsumoApi;
import br.com.alura.fipeTable.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner scan = new Scanner(System.in);
    private final String ENDERECO_INIT = "https://parallelum.com.br/fipe/api/v1/";
    private final ConverteDados conversor = new ConverteDados();
    private final ConsumoApi consumo = new ConsumoApi();

    public void exibeMenu(){

        System.out.println("***********************************");
        System.out.println("Digite um número da lista abaixo\n");
        System.out.println("""
                1-Carros
                2-Caminhões
                3-Motos""");
        System.out.println("***********************************");

        //Showing car brand

        String veicAns = scan.next();
        String end;

        if(veicAns.equals("1")){
            end = "carros/marcas/";
        } else if(veicAns.equals("2")) {
            end = "caminhoes/marcas/";
        }else{
            end = "motos/marcas/";
        }

        String endereco = ENDERECO_INIT + end;

        String json = consumo.obterDados(endereco);
        System.out.println(json);

        List<Veiculo> marcas = conversor.obterLista(json, Veiculo.class);
        marcas.forEach(System.out::println);

        //Showing brand models

        System.out.println("\nDigite um código dos mostrados acima");
        String modelAns = scan.next();

        endereco = endereco + modelAns + "/modelos/";
        json = consumo.obterDados(endereco);
        Modelos modelos = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos desta marca: ");
        modelos.modelos().stream()
                .sorted(Comparator.comparing(Veiculo::nome))
                .forEach(System.out::println);

        //Showing a specific model

        System.out.println("Digite um trecho do carro");
        String carroEspecifico = scan.next();
        System.out.println();

        List<Veiculo> modeloComFiltro = modelos.modelos().stream()
                .filter(v -> v.nome().toLowerCase().contains(carroEspecifico.toLowerCase()))
                        .collect(Collectors.toList());

        modeloComFiltro.forEach(System.out::println);

        //Showing all cars from that specific model with years and descriptions

        System.out.println("\nDigite um código");
        String tipoModelo = scan.next();
        endereco = endereco + tipoModelo + "/anos/";
        json = consumo.obterDados(endereco);
        List<Veiculo> anos = conversor.obterLista(json, Veiculo.class);
        List<Carro> carro = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAno = endereco + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAno);
            Carro veiculoAtual = conversor.obterDados(json, Carro.class);
            carro.add(veiculoAtual);
        }

        carro.forEach(System.out::println);

    }

}
