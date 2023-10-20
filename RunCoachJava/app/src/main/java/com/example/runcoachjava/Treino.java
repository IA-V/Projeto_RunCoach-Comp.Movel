package com.example.runcoachjava;

import java.time.Duration;
import java.util.List;

public class Treino {
    // Informaçoes sobre o percurso
    private double distancia;
    private Duration tempoPlan;
    private int qtdPontosPercurso;
    private List<Localizacao> pontosPercurso;
    public Treino(double distancia, Duration tempoPlan, int qtdPontosPercurso, List<Localizacao> pontosPercurso) {
        this.distancia = distancia;
        this.tempoPlan = tempoPlan;
        this.qtdPontosPercurso = qtdPontosPercurso;
        this.pontosPercurso = pontosPercurso;
    }
}
