package com.example.runcoachjava;

public class Usuario {
    private String nome;
    private double peso;
    private double altura;
    private int idade;
    public Usuario(String nome, double peso, double altura, int idade) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;

    }

    public void cadastrarUsuario(MqttHandler client) {
        client.publish("Novo Usuário", this.toString());
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nPeso: " + this.peso + "\nAltura: " + this.altura + "\nIdade: " + this.idade;
    }
}
