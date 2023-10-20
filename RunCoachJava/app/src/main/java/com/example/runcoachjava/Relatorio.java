package com.example.runcoachjava;

import java.time.Duration;
import java.util.List;

public class Relatorio {

    private double freqMedia;
    private List<Double> freqPorPonto;
    private double calQueimadas;
    private double distPercorrida;
    private Duration tempoDecorrido;
    private String ritmoMedio;
    public Relatorio(double freqMedia, List<Double> freqPorPonto, double calQueimadas, double distPercorrida, Duration tempoDecorrido, String ritmoMedio) {
        this.freqMedia = freqMedia;
        this.freqPorPonto = freqPorPonto;
        this.calQueimadas = calQueimadas;
        this.distPercorrida = distPercorrida;
        this.tempoDecorrido = tempoDecorrido;
        this.ritmoMedio = ritmoMedio;
    }
}
