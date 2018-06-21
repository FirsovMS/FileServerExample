package com.company.services.FileWrapper;

import java.util.Iterator;

public interface VFS {
    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsoulutePath(String file);

    byte[] getBytes(String file);

    Iterator<String> getIterator(String startDir);
}
