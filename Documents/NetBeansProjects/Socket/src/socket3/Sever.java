/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jasin
 */
public class Sever {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);

        Socket socket = serverSocket.accept();

//----------------------------------------------- รับไฟล์ -----------------------------------------------------------------
        InputStream inputStream = socket.getInputStream();
        int sizeBuffer = socket.getReceiveBufferSize();
        OutputStream outputStream = new FileOutputStream("D:\\Server\\Server.jpg");
        byte[] buffer = new byte[sizeBuffer];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
//----------------------------------------------- รับไฟล์ ----------------------------------------------------------------- 
        outputStream.close();
        inputStream.close();
        socket.close();
    }

}
