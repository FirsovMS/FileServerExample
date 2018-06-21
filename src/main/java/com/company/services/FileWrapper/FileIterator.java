package com.company.services.FileWrapper;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FileIterator implements Iterator<String> {
    private Queue<File> files = new LinkedList<>();

    public FileIterator(String path) {
        files.add(new File(path));
    }

    @Override
    public boolean hasNext() {
        return !files.isEmpty();
    }

    @Override
    public String next() {
        File file = files.peek();
        if(file.isDirectory()) {
            for (File sf : file.listFiles()) {
                files.add(sf);
            }
        }
        return files.poll().getAbsolutePath();
    }
}
