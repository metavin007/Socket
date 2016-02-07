/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import static socket4.Chat_Server.dataInputStream;

/**
 *
 * @author jasin
 */
class Chat_Client {

    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static String msg_out = "";
    static String msg_in = "";
}

class SentDataByClient extends Chat_Client implements Runnable {

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

class ResiveDataByClient extends Chat_Client implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                msg_in = dataInputStream.readUTF();
                System.out.println("Server : " + msg_in);
            }
        } catch (Exception e) {
            System.out.println("Exception ResiveDataByServer : " + e);
        }
    }
}

public class Client extends Chat_Client {

    public static void main(String[] args) throws IOException {
        try {

            socket = new Socket("localhost", 9988);

            Thread resiveDataByClient = new Thread(new ResiveDataByClient());
            Thread sentDataByClient = new Thread(new SentDataByClient());
            sentDataByClient.start();
            resiveDataByClient.start();

            if ("exit".equals(msg_out)) {
                socket.close();
            }

        } catch (Exception e) {
            System.out.println("Exception Client : " + e);
        }
    }
}
