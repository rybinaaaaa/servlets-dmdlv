package org.rybina;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class SocketRunner {

    public static void main(String[] args) throws IOException {
//        http - 80
//        https - 443
//        tcp port
//        try (Socket socket = new Socket("google.com", 80);
        try (Socket socket = new Socket(Inet4Address.getByName("google.com"), 80);
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            outputStream.writeUTF("Hello world!");
            byte[] response = inputStream.readAllBytes();
            System.out.println(response.length);
        }
    }
}
