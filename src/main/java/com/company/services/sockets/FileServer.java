package com.company.services.sockets;

import com.company.services.FileWrapper.FileWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class FileServer {
    private static ServerSocket serverSocket;
    private Socket clientSocket = null;
    private FileWrapper fileWrapper;

    public FileServer(FileWrapper fileWrapper) throws IOException {
        this.fileWrapper = fileWrapper;
        serverSocket = new ServerSocket(8091);
        System.out.println("Server started at localhost:8091");
    }

    public void Start() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                // create a new handler in another thread
                if(clientSocket != null) {
                    System.out.println("client joined: " + clientSocket);
                    Thread clientSocketHandler = new Thread(new ClientThreadHandler(clientSocket, fileWrapper));
                    clientSocketHandler.start();
                }
            } catch (IOException e) {
                System.out.println("Connection attempt : \nMessage : " + e.getMessage());
            }
        }
    }
}
