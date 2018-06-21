import com.company.services.FileWrapper.FileWrapper;
import com.company.services.FileWrapper.VFS;
import com.company.services.FileWrapper.VFSImpl;
import com.company.services.sockets.FileClient;
import com.company.services.sockets.FileServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.zip.CRC32;

public class ServerClientFileReceiving {
    private static FileWrapper wrapper;
    private static FileServer server;
    private static FileClient client;

    @Before
    public void setUp(){
        wrapper = FileWrapper.getInstance();
    }

    /**
     * Test hava a problem : doesn't ending, because server run in another thread
     * need to
     */
    @Test
    public void TestReceivingModulesOnSocket() {
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
        serverThreead.interrupt();
    }

    @After
    public void setDown(){
        server = null;
        client = null;
        wrapper = null;
    }
}
