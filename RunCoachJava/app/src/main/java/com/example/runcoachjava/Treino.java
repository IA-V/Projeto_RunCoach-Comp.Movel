package com.example.runcoachjava;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.FileReader;

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

    public List<String> iniciarTreino(InputStream is) {
        // Pega os dados de localização, batimentos e timestamp durante o treino a cada intervalo de tempo (simulação de dataset pode entrar aqui)
        List<String> dados = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        try {
            // String csvfileString = appInfo.dataDir + File.separatorChar + "dataset_runcoach.csv";
            // File csvfile = new File(csvfileString);
            // CSVReader reader = new CSVReader(new FileReader(/*csvfile.getAbsolutePath()*/ "C:\\Users\\iagov\\AndroidStudioProjects\\RunCoachJava\\dataset_runcoach.csv"));
            String nextLine;
            reader.readLine();
            while ((nextLine = reader.readLine()) != null) {
                // nextLine[] is an array of values from the line
                // Log.i("csvTag", nextLine);
                String[] tokens = nextLine.split(",");
                dados.add(new String(tokens[1] + "," + tokens[2] + "," + tokens[3] + "," + tokens[0]));
                Log.i("csvTag", tokens[1] + "," + tokens[2] + "," + tokens[3] + "," + tokens[0]);
            }
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        }

        // dados.add(new String[]{"56.7281", "47.0600", "123", "20231024150784"});
        // ... outros pepinos a resolver
        // Localização atual é igual à última localização do treino (treino encerra aqui)
        return dados;
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
