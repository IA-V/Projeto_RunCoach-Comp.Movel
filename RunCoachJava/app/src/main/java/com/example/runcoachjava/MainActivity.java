package com.example.runcoachjava;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.runcoachjava.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.time.Duration;

import com.opencsv.CSVReader;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String BROKER_URL = "tcp://ec2-3-128-27-50.us-east-2.compute.amazonaws.com";
    private static final String CLIENT_ID = "RunCoachClient";
    private MqttHandler mqttClient;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mqttClient = new MqttHandler();
        mqttClient.connect(BROKER_URL, CLIENT_ID);

        /* Toast.makeText(this, "Publicando tópico: " + "Tópico de Teste", Toast.LENGTH_SHORT).show();
        mqttClient.publish("Tópico de Teste", "este tópico é para teste"); */
        /* Toast.makeText(this, "Subscrevendo-se ao tópico: " + "runcoach/data", Toast.LENGTH_SHORT).show();
        mqttClient.subscribe("runcoach/data"); */

        // Usuário a ser cadastrado
        Box<Usuario> usuarioBox = new Box<Usuario>(new Usuario("Fulano", 80.5, 1.85, 30));

        Localizacao local1 = new Localizacao(41.7810, 60.0054);
        Localizacao local2 = new Localizacao(41.7810, 60.0054);
        Localizacao local3 = new Localizacao(41.7810, 60.0054);

        List<Localizacao> pontosPercurso = new ArrayList<>();

        pontosPercurso.add(local1);
        pontosPercurso.add(local2);
        pontosPercurso.add(local3);

        // Dados de um treino cadastrado virão do BD | Dados de um treino a ser cadastrado serão adquiridos da UI (instanciação do obj será feita onde quer que os dados sejam adquiridos)
        Treino treino = new Treino(2530.5, 30, 25, pontosPercurso);

        // Treino a ser cadastrado
        Box<Treino> treinoBox = new Box<Treino>(treino);

        // this.cadastrarModelo(usuarioBox); // Exemplo
        // this.cadastrarModelo(treinoBox); // Exemplo

        List<String[]> dadosTreino = treino.iniciarTreino();
        // Exibir opção de subir dados ao servidor ou não (chamada ao método provavelmente será feito onde a UI é implementada, então esta parte do código não ficará aqui na Main)
        this.cadastrarDadosTreino(dadosTreino);// Se sim

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mqttClient.disconnect();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void cadastrarModelo(Box<?> boxObj) {
        mqttClient.publish("runcoach/data", boxObj.toString());
    }

    private void cadastrarDadosTreino(List<String[]> dados) {
        for (String[] stringDados :
             dados) {
            mqttClient.publish("runcoach/data", stringDados[0]+","+stringDados[1]+","+stringDados[2]+","+stringDados[3]);
        }
    }
}