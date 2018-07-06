import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.CRC32;

import org.junit.Test;

import com.company.services.FileWrapper.VFS;
import com.company.services.FileWrapper.VFSImpl;

import junit.framework.Assert;

public class FileSystemTest {
	
	@Test
	public void GetBytesArrayFromFileTest() throws IOException {
		// TODO: write this test
		String root = System.getProperty("user.dir");
		VFS vfs = new VFSImpl(root);
		
		// generate dummy file
		byte[] dummy = new byte[4096];
		Random rand = new Random();
		rand.nextBytes(dummy);
		
		// write dummy
		Path get = Paths.get(root + "/dummy.file");
		Files.write(get, dummy);
		
		// read from fs
		byte[]loaded = vfs.getBytes("dummy.file");
		
		// check file
		long dummyCRC32 = doChecksum(dummy);
		long loadedCRC32 = doChecksum(loaded);
		
		assertArrayEquals(loaded, dummy);
	}

	
	private long doChecksum(byte[] arr) {
		CRC32 crc32 = new CRC32();
		crc32.update(arr);
		return crc32.getValue();
	}
}
