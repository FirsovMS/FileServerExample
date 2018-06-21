package com.company.services.sockets;

import com.company.services.FileWrapper.FileWrapper;

import java.io.*;
import java.net.Socket;

/**
 * This file created for using in Client app
 */
public class FileClient {
    private final FileWrapper fileWrapper;
    private Socket socket;

    public FileClient(FileWrapper fileWrapper) throws IOException {
        this.fileWrapper = fileWrapper;
        socket = new Socket("localhost", 8091);
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
        byte[] buffer = new byte[1024];
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
