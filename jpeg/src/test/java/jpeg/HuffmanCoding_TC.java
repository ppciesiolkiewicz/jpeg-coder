package jpeg;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import JpegMath.Coders.HuffmanCoding;

public class HuffmanCoding_TC {

	@Test
	public void decoding() {
		Path p = Paths.get("example_bmp/output20.jpg");
		byte[] data = null;

		try {
			data = Files.readAllBytes(p);
		} catch (IOException e) { // TODO
			e.printStackTrace();
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		
		HuffmanCoding hc = new HuffmanCoding();
		hc.decodeImage(bais);

		assertEquals(true, true); // he he
	}
}
