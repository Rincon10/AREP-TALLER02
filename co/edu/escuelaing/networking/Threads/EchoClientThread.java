package co.edu.escuelaing.networking.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientThread extends Thread{
    protected Socket echoSocket;
    protected PrintWriter out = null;
    protected BufferedReader in = null;

    public EchoClientThread(Socket clientSocket) {
        this.echoSocket = clientSocket;
    }

    public EchoClientThread() throws IOException {
        this.echoSocket = new Socket("127.0.0.1", 35000);
    }

    public void closeConnection(BufferedReader stdIn) throws IOException {
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }

    public void startConnection() throws IOException {

        try {

            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }
        closeConnection( stdIn );


    }

    @Override
    public void run() {
        super.run();
        try {
            startConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        int times = 5;

        for (int i = 0; i < times; i++) {
            System.out.print("Hola" + i);
            Thread thread = new EchoClientThread( );
            thread.start();
            System.out.print("Hola" + i);
        }
    }
}
