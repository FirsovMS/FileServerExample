package com.company.Services.sockets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

import com.company.Services.FileWrapper.FileWrapper;

public class ClientThreadHandler implements Runnable{
    private final Socket clientSocket;
    private final FileWrapper fileWrapper;
    private BufferedReader in;

    public ClientThreadHandler(Socket clientSocket, FileWrapper fileWrapper) {
        this.clientSocket = clientSocket;
        this.fileWrapper = fileWrapper;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String filename;
            // get all available modules
            Map<String, File> modules = fileWrapper.getModulesMap();

            while ((filename = in.readLine()) != null) {
                if(!filename.isEmpty()){
                    File selectedModule = modules.get(filename);
                    sendFile(selectedModule);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send file to another socket
     * message format : {"fileName', (long)size, (byte[]) file }
     * @param file
     * @throws IOException
     */
    private void sendFile(File file) throws IOException {
        // Notify : dangerous type conversion
        byte[] buffer = new byte[(int)file.length()];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        dataInputStream.readFully(buffer, 0, buffer.length);

        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataOutputStream.writeUTF(file.getName());
        dataOutputStream.writeLong(buffer.length);
        dataOutputStream.write(buffer, 0, buffer.length);
        
        dataOutputStream.flush();
    }
}
