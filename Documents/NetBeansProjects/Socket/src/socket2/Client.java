/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author jasin
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9988);

//-------------------------------------------ส่งไฟล์-----------------------------------------------------------------
        File file = new File("D:\\Client\\ClientFile.txt");
        long fileLength = file.length();
        InputStream InputStream = new FileInputStream(file);
        OutputStream outputStream = socket.getOutputStream();
        byte[] buffer = new byte[(int) fileLength];
        int len = -1;
        while ((len = InputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        //-------------------------------------------ส่งไฟล์-----------------------------------------------------------------      
        outputStream.close();
        InputStream.close();
        socket.close();
    }
}
