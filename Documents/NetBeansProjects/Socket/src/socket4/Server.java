/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author jasin
 */
class Chat_Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static String msg_out = "";
    static String msg_in = "";
}

class SentDataByServer extends Chat_Server implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                msg_out = scanner.nextLine();
                dataOutputStream.writeUTF(msg_out);
            }
        } catch (Exception e) {
            System.out.println("Excepton SentDataByServer : " + e);
        }
    }
}

class ResiveDataByServer extends Chat_Server implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                msg_in = dataInputStream.readUTF();
                System.out.println("Client : " + msg_in);
            }
        } catch (Exception e) {
            System.out.println("Exception ResiveDataByServer : " + e);
        }
    }
}

public class Server extends Chat_Server {

    public static void main(String[] args) throws IOException {
        try {
            serverSocket = new ServerSocket(9988);
            socket = serverSocket.accept();

            Thread sentDataByServer = new Thread(new SentDataByServer());
            Thread resiveDataByServer = new Thread(new ResiveDataByServer());
            resiveDataByServer.start();
            sentDataByServer.start();

            if ("exit".equals(msg_out)) {
                socket.close();
            }

        } catch (Exception e) {
            System.out.println("Exception Server : " + e);
        }
    }
}
