package jpeg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DataObjects.Binary;

public class Binary_TC {

	@Test
	public void test() {
		Binary b = new Binary("0");
		assertEquals(0, b.intValue());
		b = new Binary("1");
		assertEquals(1, b.intValue());
		b = new Binary("00");
		assertEquals(0, b.intValue());
		b = new Binary("01");
		assertEquals(1, b.intValue());
		b = new Binary("10");
		assertEquals(2, b.intValue());
	}

}
