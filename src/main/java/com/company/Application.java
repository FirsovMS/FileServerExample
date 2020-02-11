package com.company;

import com.company.Services.DdService.DBService;
import com.company.Services.FileWrapper.FileWrapper;
import com.company.Services.servlets.ModuleInfoServlet;
import com.company.Services.sockets.FileServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {
    private FileWrapper fileWrapper;
    private FileServer fileServer;
    private ModuleInfoServlet servlet;
    private DBService dbService;


    public static void main(String[] args) {
		// Load FileWrapper, Properties, and run ServletServices, SocketService
        Application application = new Application();
        application.LoadProperties("config.properties");
        application.InitializeServices();
    }

    private void InitializeServices(){
        try {
            dbService = new DBService();
            servlet = new ModuleInfoServlet(dbService);

            fileWrapper = FileWrapper.getInstance();
            fileServer = new FileServer(fileWrapper);
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private void LoadProperties(String propertiesFileName){
        Properties properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if(in != null) {
                properties.load(in);
               System.setProperties(properties);
            }else{
                throw new FileNotFoundException(String.format("Properties file: %s not founded!", propertiesFileName));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
