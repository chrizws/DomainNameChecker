package org.xyz;

import java.io.*;

public class App {

    public static void main( String[] args ) {

        Client client = new Client();
        client.createClient();

        try (BufferedReader input = new BufferedReader(new FileReader("domains.txt"))) {

            String s;
            while ((s = input.readLine()) != null) {
                s = s.substring(0, s.length()-2);
                client.setParameter(s.toLowerCase() + ".ca").createRequest();

                String response = client.getResponse().body();
                System.out.println(response);

                Thread.sleep(1000);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        } finally {
            client.close();
        }

    }
}
