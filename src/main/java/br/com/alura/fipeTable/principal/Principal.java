package br.com.alura.fipeTable.principal;

import br.com.alura.fipeTable.model.Veiculo;
import br.com.alura.fipeTable.service.ConsumoApi;
import br.com.alura.fipeTable.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

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

        String veicAns = scan.next();
        String end;

        if(veicAns.equals("1")){
            end = "carros/marcas/";
        } else if(veicAns.equals("2")) {
            end = "caminhoes/marcas/";
        }else{
            end = "motos/marcas/";
        }

        String json = consumo.obterDados(ENDERECO_INIT + end);
        System.out.println(json);
        List<Veiculo> marcas = conversor.obterLista(json, Veiculo.class);
        marcas.forEach(System.out::println);

        System.out.println("\nDigite um código dos mostrados acima");
        String modelAns = scan.next();
        json = consumo.obterDados(ENDERECO_INIT + end + modelAns + "/models/");
        List<Veiculo> modelos = conversor.obterLista(json, Veiculo.class);
        modelos.forEach(System.out::println);

    }

}
