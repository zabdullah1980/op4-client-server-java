package OP4_Pruefung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        boolean useJson = true;

        System.out.println("Server läuft...hau rein");

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            Socket socket = serverSocket.accept();
            System.out.println("Client verbunden ✅");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            if (useJson) {

                // 🔥 Version 2: JSON
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


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String code = in.readLine();


            if ("007".equals(code) || "008".equals(code)) {
                out.println("Viel Erfolg Agent! ✅");
            } else {
                out.println("Falscher Code ❌");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}