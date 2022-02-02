package co.edu.escuelaing.networking;

import java.net.MalformedURLException;
import java.net.URL;

public class URLParser {
    private static final String google = "https://google.com:80/docs/index.html?name=hola&val=5#main";

    public static void main(String[] args) {


        try {
            URL googleURL = new URL(google); //El objeto no se esta conectando a internet, solo maneja un nombre
            System.out.println("Protocol: " + googleURL.getProtocol());
            System.out.println("Host: " + googleURL.getHost());
            System.out.println("Port: " + googleURL.getPort());
            System.out.println("Authority: " + googleURL.getAuthority());
            System.out.println("Path: " + googleURL.getPath());
            System.out.println("Query: " + googleURL.getQuery());
            System.out.println("File: " + googleURL.getFile());
            System.out.println("Ref: " + googleURL.getRef()); // es una referencia a una parte del documento
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
    }
}