package com.example.runcoachjava;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler {

    private MqttClient client;

    public void connect(String brokerUrl, String clientId) {
        try {
            // Set up the persistence layer
            MemoryPersistence persistence = new MemoryPersistence();

            // Initialize the MQTT client
            client = new MqttClient(brokerUrl, clientId, persistence);

            // Set up the connection options
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            // Connect to the broker
            client.connect(connectOptions);

            Log.i("conexao bem sucedida", "CONECTADO COM SUCESSO!!!");
        } catch (MqttException e) {
            Log.i("erro de conexão", "Não conectou!");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
            Log.i("publicação bem sucedida", "Tópico publicado com sucesso!");
        } catch (MqttException e) {
            Log.i("Erro na publicação", "publicação falhou!");
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.i("conexão perdida", cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.i("mensagem recebida", "Tópico: " + topic + "\nMensagem: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        Log.i("mensagem enviada", new String(token.getMessage().getPayload())); // log ou toast
                    } catch (MqttException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Log.i("subscrição bem sucedida", "Subscrição realizada com sucesso!");
        } catch (MqttException e) {
            Log.i("Erro na subscrição", "Subscrição falhou!");
            e.printStackTrace();
        }
    }
}
