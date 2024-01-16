package org.rybina;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UrlExample {

    public static void main(String[] args) throws URISyntaxException, IOException {
//        через url мы можем также подключаться к файлам
        URL url = (new URI("https://www.google.com").toURL());

        URLConnection urlConnection = url.openConnection();

//        Для того, чтобы мы могли отправлять информацию. по умолчанию мы информанцию мы получаем, но конечно гугл информацию эту не примет и отправит 405 ошибку
//        urlConnection.setDoOutput(true);
//
//        String data = "param1=value1&param2=value2";

        // Получаем OutputStream для записи данных в запрос
//        try (OutputStream outputStream = urlConnection.getOutputStream()) {
//            byte[] input = data.getBytes(StandardCharsets.UTF_8);
//            outputStream.write(input, 0, input.length);
//        }


        System.out.println(urlConnection.getContent());
    }
}
