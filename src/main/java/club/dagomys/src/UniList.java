package club.dagomys.src;


import jdk.internal.util.ArraysSupport;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class UniList<T> implements CustomList<T> {
    private static final int DEF_CAPACITY = 4;
    private Object[] array;
    private static final Object[] EMPTY_ARRAY = {};
    private static final Object[] DEF_ARRAY = {};
    private int size;

    public UniList() {
        this.array = DEF_ARRAY;
    }

    private void add(T element, Object[] elementData, int size) {
        if (size == elementData.length)
            elementData = increase(size);
        elementData[size] = element;
        this.size = size + 1;
    }

    private Object[] increase(int minArrayCapacity) {
        int oldCapacity = array.length;
        if (oldCapacity > 0 || array != DEF_ARRAY) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minArrayCapacity - oldCapacity,
                    oldCapacity >> 1);
            return array = Arrays.copyOf(array, newCapacity);
        } else {
            return array = new Object[Math.max(DEF_CAPACITY, minArrayCapacity)];
        }
    }

    private T element(int index){
        return (T) array[index];
    }

    @Override
    public boolean add(T element) {
        add(element, array, size);
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + "Size " + size);
        final int size;
        Object[] newArray;
        if ((size = this.size) == (newArray = array).length)
            newArray = increase(size + 1);
        System.arraycopy(newArray, index, newArray, index + 1, size - 1);
        newArray[index] = element;
        this.size = size + 1;
        return true;
    }

    private void remove(Object[] inboundArray, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(inboundArray, index + 1, inboundArray, index, newSize - index);
        inboundArray[this.size = newSize] = null;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return element(index);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Object[] tempData = array;
        T oldElement = (T) tempData[index];
        remove(array, index);
        return oldElement;
    }

    @Override
    public void clear() {
        final Object[] newArray = array;
        for (int to = this.size, i = this.size = 0; i < to; i++)
            newArray[i] = null;
    }

    @Override
    public int getSize() {
        return size;
    }


    @Override
    public T sort() {
        return null;
    }


    @Override
    public T sort(Comparator<T> comparator) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }
}
