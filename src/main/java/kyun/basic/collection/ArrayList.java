package kyun.basic.collection;

public class ArrayList implements List {

    private Object[] array = new Object[0];
    private int size;

    @Override
    public void add(Object obj) {
        Object[] newArray = new Object[++size];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[size - 1] = obj;
        array = newArray;
    }

    @Override
    public void update(int index, Object value) {
        if (size == 0)
            throw new RuntimeException("배열의 사이즈가 0입니다.");
        array[index] = value;
    }

    @Override
    public void remove(int index) {
        if (size == 0)
            return;
        Object[] newArray = new Object[--size];
        int arrayIndex = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (i == index)
                continue;
            newArray[arrayIndex++] = array[i];
        }
        array = newArray;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object get(int i) {
        return array[i];
    }
}
