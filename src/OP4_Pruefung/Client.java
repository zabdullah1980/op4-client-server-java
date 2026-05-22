package OP4_Pruefung;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        boolean useJson = true;

        System.out.println("Client erfolgreich gestartet...");

        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Auftrag erfolgreich erhalten:");

            String line;

            if (useJson) {

                // ✅ Version 2: JSON
                StringBuilder jsonBuilder = new StringBuilder();

                while (!(line = in.readLine()).equals("END")) {
                    jsonBuilder.append(line);
                }

                String json = jsonBuilder.toString();


                json = json.replace("{", "")
                        .replace("}", "")
                        .replace("\"", "");

                String[] parts = json.split(",");

                System.out.println("📦 Mission Data:");
                for (String part : parts) {
                    System.out.println(part.trim());
                }

            } else {

                // ✅ Version 1: Text
                while (!(line = in.readLine()).equals("END")) {
                    System.out.println(line);
                }
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
