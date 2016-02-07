/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jasin
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9988);

        Socket socket = serverSocket.accept();

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("ได้ยินแล้วClient"); // ส่งคำทักทายให้ Client

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String msg_in = dataInputStream.readUTF(); // รับคำทักทายกลับมาจาก Client
        System.out.println("Client : " + msg_in);

        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

}
