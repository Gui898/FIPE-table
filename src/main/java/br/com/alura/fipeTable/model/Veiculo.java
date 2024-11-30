package br.com.alura.fipeTable.model;

public record Veiculo(String codigo, String nome) {
    public String toString(){
        return "Cód = " + codigo + " | Nome: " + nome;
    }
}
