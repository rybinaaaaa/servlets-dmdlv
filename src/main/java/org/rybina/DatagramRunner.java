package org.rybina;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;


public class DatagramRunner {

//    TCP connection
    public static void main(String[] args) throws IOException {
            // Создаем DatagramSocket, представляющий собой сокет для отправки и приема DatagramPacket
            try (DatagramSocket datagramSocket = new DatagramSocket()) {
                // Создаем массив байтов для хранения данных, которые будут отправлены
                byte[] bytes = "Hello from UDP client".getBytes();

                // Создаем DatagramPacket с данными, адресом назначения (localhost) и портом (7777). Адрес и порт исп только при отправке пакета
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, Inet4Address.getByName("localhost"), 7777);

                // Отправляем DatagramPacket с использованием DatagramSocket
                datagramSocket.send(datagramPacket);
            }
    }
}

