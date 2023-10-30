package com.example.runcoachjava;

public class Usuario {
    private String nome;
    private double peso;
    private double altura;
    private String dataNasc;
    public Usuario(String nome, double peso, double altura, String dataNasc) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.dataNasc = dataNasc;

    }

    public void cadastrarUsuario(MqttHandler client) {
        client.publish("Novo Usuário", this.toString());
    }

    @Override
    public String toString() {
        return this.nome + "," + this.dataNasc + "," + this.peso + "," + this.altura;
    }
}
