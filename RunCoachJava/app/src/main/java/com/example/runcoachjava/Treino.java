package com.example.runcoachjava;

import java.time.Duration;
import java.util.List;

public class Treino {
    // Informaçoes sobre o percurso
    private double distancia;
    private double tempoPlan;
    private int qtdPontosPercurso;
    private List<Localizacao> pontosPercurso;
    public Treino(double distancia, double tempoPlan, int qtdPontosPercurso, List<Localizacao> pontosPercurso) {
        this.distancia = distancia;
        this.tempoPlan = tempoPlan;
        this.qtdPontosPercurso = qtdPontosPercurso;
        this.pontosPercurso = pontosPercurso;
    }

    public void cadastrarTreino(MqttHandler client) {
        client.publish("Novo Treino", this.toString());
    }

    @Override
    public String toString() {
        String localizacoes = "";
        int count = 1;
        for (Localizacao pontoPercurso :
             this.pontosPercurso) {
            localizacoes += "\n" + Integer.toString(count) + " - " + pontoPercurso.toString();
        }
        return "Distância: " + this.distancia + "\nTempo planejado: " + this.tempoPlan + "\nQtd. de pontos de percurso: " + this.qtdPontosPercurso + "\nPontos do percurso: " + localizacoes;
    }
}
