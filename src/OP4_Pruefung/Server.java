package OP4_Pruefung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        System.out.println("Server läuft...hau rein");

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            Socket socket = serverSocket.accept();
            System.out.println("Client verbunden, was kann ich für Sie tun! ✅");


            BufferedReader fileReader = new BufferedReader(new FileReader("resources/Auftrag.txt"));


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
            }

            out.println("END");


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String code = in.readLine();


            if ("007".equals(code)) {
                out.println("Viel Erfolg Agent! Mach, was du willst ✅");
            } else {
                out.println("Falscher Code, leider kein Zugriff erlaubt ❌");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}