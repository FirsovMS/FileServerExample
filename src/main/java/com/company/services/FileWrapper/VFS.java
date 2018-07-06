package com.company.services.FileWrapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

public interface VFS {
    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsoulutePath(String file);

    Iterator<String> getIterator(String startDir);

	byte[] getBytes(String file) throws IOException;
}
