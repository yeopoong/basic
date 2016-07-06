package kyun.basic.collection;

public interface List {
	public void add(Object obj);
	public void update(int index, Object value);
	public void remove(int index);
	
	public int getSize();
	public Object get(int i);
}
