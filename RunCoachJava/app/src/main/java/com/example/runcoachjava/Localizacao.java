package com.example.runcoachjava;

public class Localizacao {
    private double longitude;
    private double latitude;
    // private double altura;
    public Localizacao(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        // this.altura = altura;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
