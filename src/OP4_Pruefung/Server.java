package OP4_Pruefung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        boolean useJson = true;

        System.out.println("Thread: " + Thread.currentThread().getId());

        System.out.println("Server läuft...");

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client verbunden ✅");

                new Thread(() -> handleClient(socket, useJson)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleClient(Socket socket, boolean useJson) {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            if (useJson) {

                String json = "{ \"target\": \"CIA Headquarters\", " +
                        "\"description\": \"Get confidential computer\", " +
                        "\"location\": \"USA\" }";

                out.println(json);

            } else {

                BufferedReader fileReader = new BufferedReader(
                        new FileReader("resources/Auftrag.txt"));

                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
            }

            out.println("END");

            String code = in.readLine();

            if ("007".equals(code) || "008".equals(code)) {
                out.println("Viel Erfolg Agent! ✅");
            } else {
                out.println("Falscher Code ❌");
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
