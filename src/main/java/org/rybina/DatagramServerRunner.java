package org.rybina;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class DatagramServerRunner {

    public static void main(String[] args) throws IOException {
        try (DatagramSocket datagramSocket = new DatagramSocket(7777)){
            byte[] buffer = new byte[80];
//            Адрес и порт исп только при отправке пакета
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);

//            datagram пакет переписыавет тот массив, что мы передали в него
            System.out.println(new String(buffer));
        }
    }
}
