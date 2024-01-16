package org.rybina;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) throws IOException  {
        try (ServerSocket serverSocket = new ServerSocket(7777);
//      serverSocket.accept() - принимает только одно соединение с клиентом, далее это будет решаемо с помощью многопоточности
//             socket - первый клиент, что подключился
             Socket socket = serverSocket.accept();
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            String request = inputStream.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                String response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
        }
    }
}
