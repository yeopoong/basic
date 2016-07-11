package kyun.basic.collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayListTest {
	static List list;
	
	@BeforeClass
	public static void setUp() {
		list = new ArrayList();
        list.add("init");
	}

	@Test
	public void add() {
		list.add("add");
        Assert.assertEquals(2, list.getSize());
	}

    @Test
    public void get() {
        Assert.assertEquals("init", list.get(0));
    }

    @Test
    public void getSize() {
        Assert.assertEquals(2, list.getSize());
    }

	@Test
    public void remove() {
        list.remove(1);
        Assert.assertEquals(1, list.getSize());
    }

    @Test
	public void update() {
		list.update(0, "updated");
		Assert.assertEquals("updated", list.get(0));
	}
}
