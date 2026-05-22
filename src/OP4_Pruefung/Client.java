package OP4_Pruefung;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        System.out.println("Client erfolgreich gestartet...");

        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {


            String line;
            System.out.println("Auftrag erfolgreich erhalten:");

            while (!(line = in.readLine()).equals("END")) {
                System.out.println(line);
            }


            System.out.print("Geben Sie den Code ein: ");
            String code = console.readLine();


            out.println(code);

            String response = in.readLine();
            System.out.println("Server Antwort: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
