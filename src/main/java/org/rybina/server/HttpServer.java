package org.rybina.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
//            accept блокирует потокк
            Socket socket = serverSocket.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
//        socket мы передали чтобы в конце его закрыть
        try (socket;
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             OutputStream outputStream = socket.getOutputStream();) {
//        1 шаг - обработка запроса
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));
//        2 шаг - обработка ответа
            byte[] body = "Hi client!".getBytes();
            byte[] headers = """
                    HTTP/1.1 200 OK
                    Content-Type: text/html
                    Content-Length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
