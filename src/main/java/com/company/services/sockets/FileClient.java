package com.company.Services.sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.company.Services.FileWrapper.FileWrapper;

/**
 * This file created for using in Client app
 */
public class FileClient {
    private static final String host = System.getProperty("server.host");
    private static final int port = Integer.valueOf(System.getProperty("server.port"));
    private static final int bufferSize = Integer.valueOf(System.getProperty("server.bufferSize"));

    private final FileWrapper fileWrapper;
    private Socket socket;

    public FileClient(FileWrapper fileWrapper) throws IOException {
        this.fileWrapper = fileWrapper;
        socket = new Socket(host, port);
    }

    /**
     * Send 'fileName' to server and receive choosed file
     * @param filename
     */
    public void receiveFile(String filename) throws Exception {
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        // send filename to server
        printStream.println(filename);
        // and receive file
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = new FileOutputStream("tmp_" + filename);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int bytesRead;  // byte counter
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[bufferSize];
        while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            size -= bytesRead;
        }
        // write complete
        outputStream.close();
        inputStream.close();
        System.out.println("File " + filename + " already received!");
    }
}
