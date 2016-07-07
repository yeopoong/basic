package kyun.basic.collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListTest {
	static List list;
	
	@BeforeClass
	public static void setUp() {
		list = new ArrayList();
		list.add("setUp");
	}
	
	@Test
	public void add() {
		list.add("add");
		Assert.assertEquals(2, list.getSize());
	}
	
	@Test
	public void update() {
		list.update(0, "updated");
		Assert.assertEquals("updated", list.get(0));
	}
	
	@Test
	public void remove() {
		list.remove(0);
		Assert.assertEquals(1, list.getSize());
	}
}
