package com.company.services.FileWrapper;

import com.company.services.sockets.FileServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.*;

public class FileWrapper {
    private static  FileWrapper instance;

    private VFS vfs;
    private String CWD = System.getProperty("user.dir");

    private FileWrapper(){
        vfs = new VFSImpl(CWD);
    }

    public static FileWrapper getInstance() {
        if(instance == null){
            instance = new FileWrapper();
        }
        return instance;
    }

    public void replaceFile(File file, String path) throws IOException {
        if(vfs.isExist(path)) {
            Files.move(file.toPath(), Paths.get(path), REPLACE_EXISTING);
        }
        Files.move(file.toPath(), Paths.get(path));
    }

    public List<File> getModulesList(){
        Pattern pattern = Pattern.compile(".jar");
        Iterator<String> fileIterator = vfs.getIterator("files");
        List<File> files = new LinkedList<>();
        while (fileIterator.hasNext()) {
            File file = new File(fileIterator.next());
            String filename = file.getName();
            Matcher match = pattern.matcher(filename);
            if(match.find())
                files.add(file);
        }
        return files;
    }

    public Map<String, File> getModulesMap(){
        Pattern pattern = Pattern.compile(".jar");
        Iterator<String> fileIterator = vfs.getIterator("files");
        Map<String, File> files = new HashMap<>();
        while (fileIterator.hasNext()) {
            File file = new File(fileIterator.next());
            String filename = file.getName();
            Matcher match = pattern.matcher(filename);
            if(match.find())
                files.put(filename, file);
        }
        return files;
    }
}
