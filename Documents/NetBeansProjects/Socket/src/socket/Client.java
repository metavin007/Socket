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
import java.net.Socket;

/**
 *
 * @author jasin
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9988);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("ได้ยินไหมServer"); // ส่งคำทักทายให้ Server

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String msg_in = dataInputStream.readUTF(); // รับคำทักทายกลับมาจาก Server
        System.out.println("Server : " + msg_in); 

        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

}
