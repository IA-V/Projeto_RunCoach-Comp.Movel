package com.example.runcoachjava;

public class Localizacao {
    private double longitude;
    private double latitude;
    private double altura;
    public Localizacao(double longitude, double latitude, double altura) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Localizacao {" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", altura=" + altura +
                '}';
    }
}
