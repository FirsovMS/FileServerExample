package com.company.Services.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.company.Services.FileWrapper.FileWrapper;

public class FileServer {
    private static ServerSocket serverSocket;
    private static final int port = Integer.valueOf(System.getProperty("server.port"));
    private Socket clientSocket = null;
    private FileWrapper fileWrapper;

    public FileServer(FileWrapper fileWrapper) throws IOException {
        this.fileWrapper = fileWrapper;
        serverSocket = new ServerSocket(port);
        System.out.println("Server started at localhost:" + port);
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
