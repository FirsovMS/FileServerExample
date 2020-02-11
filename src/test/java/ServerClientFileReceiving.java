import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.company.Services.FileWrapper.FileWrapper;
import com.company.Services.sockets.FileClient;
import com.company.Services.sockets.FileServer;

public class ServerClientFileReceiving {
    private static FileWrapper wrapper;
    private static FileServer server;
    private static FileClient client;

    @Before
    public void setUp(){
        wrapper = FileWrapper.getInstance();
    }

    /**
     * Test hava a problem : doesn't ending, because server run in another thread -> need stoped
     * @throws InterruptedException 
     */
    @Test
    @Ignore
    public void TestReceivingModulesOnSocket() throws InterruptedException {
        // create server in another thread
        Thread serverThreead = new Thread(() -> {
            try {
                server = new FileServer(wrapper);
                server.Start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThreead.start();

        File dummyFile = wrapper.getModulesList().get(0);
        System.out.println("Dummy file is: " + dummyFile.getName());

        // start client
        try {
            client = new FileClient(wrapper);
            client.receiveFile(dummyFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThreead.join();
    }

    @After
    public void setDown(){
        server = null;
        client = null;
        wrapper = null;
    }
}
