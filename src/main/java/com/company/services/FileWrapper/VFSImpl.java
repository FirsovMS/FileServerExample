package com.company.Services.FileWrapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    private byte[] concat(byte[] a1, byte[] a2) {
    	byte[] out = new byte[a1.length + a2.length];
    	for(int i = 0; i < out.length; ++i) {
    		out[i] = a1[i];
    		out[i+a1.length] = a2[i];
    	}
    	return out;
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(root + "/" + startDir);
    }

	@Override
	public byte[] getBytes(String file) throws IOException {
		Path path = Paths.get(root + "/" + file);
		return Files.readAllBytes(path);
	}
}
