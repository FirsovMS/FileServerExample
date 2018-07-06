package com.company.services.FileWrapper;

import java.io.*;
import java.util.Iterator;

public class VFSImpl implements VFS {
    private String root;

    public VFSImpl(String root){
        this.root = root;
    }

    @Override
    public boolean isExist(String path) {
        return new File(root + "/" + path).exists();
    }

    @Override
    public boolean isDirectory(String path) {
        return new File(root + "/" + path).isDirectory();
    }

    @Override
    public String getAbsoulutePath(String file) {
        return new File(root + "/" + file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) {
//        byte[] buffer = new byte[1024];
//        if(!this.isExist(file)) return new byte[] {};
//        InputStream stream = null;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            stream = new FileInputStream(file);
//            int numRead = 0;
//            while ((numRead = stream.read(buffer)) > -1) {
//                outputStream.write(buffer, 0, numRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(stream !=  null) stream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return outputStream.toByteArray();
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(root + "/" + startDir);
    }
}
