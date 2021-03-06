package com.company.Services.FileWrapper;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWrapper {
    private static final String fileDirectory = System.getProperty("user.dir");
    private final VFS vfs;
    private static  FileWrapper instance;

    private FileWrapper(){
        vfs = new VFSImpl(fileDirectory);
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
