package club.dagomys.src;


import jdk.internal.util.ArraysSupport;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;



/**
 * Реализация класса который имплементирует созданный в учебных целях интерфейс CustomList<T>
 * Создание собственного ArrayList<>
 *
 * @param <T>
 */
public class UniList<T> implements CustomList<T> {
    private static final int DEF_CAPACITY = 4;
    private Object[] array;
    private static final Object[] DEF_ARRAY = {};
    private int size;

    /**
     * Создает список с заданным размером
     *
     * @param size - размер создаваемого списка
     */
    public UniList(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size <= 0!");
        } else
            array = new Object[size];
    }

    /**
     * Дефолтный конструктор, создающий спиок размерностью DEF_CAPACITY
     */

    public UniList() {
        this.array = (T[]) new Object[DEF_CAPACITY];
    }

    /**
     * Метод добавляет element в конец списка. Если достигается конец массива, массив увеличивается вдвое
     *
     * @param element добавляемый элемент
     * @return true при успешном добавлении в список
     */

    @Override
    public boolean add(T element) {
        if (size == array.length)
            array = Arrays.copyOf(array, array.length * 2);
        array[size++] = element;
        return true;
    }

    /**
     * Добавление элемента в позицию index. В случае, если index < 0 или index > size выбрасывается исключение IndexOutOfBoundsException
     * При успешном добавлении элемента, все элементы находящиеся правее index перемещаются index + 1
     *
     * @param index   - позиция для добавления
     * @param element - добавляемый элемент
     * @return возвращает true при успешном добавлении либо выбрасывается исключение IndexOutOfBoundsException;
     */
    @Override
    public boolean add(int index, T element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index " + index + " Size " + size);
        final int size;
        Object[] newArray;
        if ((size = this.size) == (newArray = array).length)
            newArray = increase(size + 1);
        System.arraycopy(newArray, index, newArray, index + 1, size - 1);
        newArray[index] = element;
        this.size = size + 1;
        return true;
    }

    @Override
    public void set(T element, int index) {
        Objects.checkIndex(index, size);
        array[index] = element;
    }

    /**
     * Возвращает элемент по индексу
     *
     * @param index - индекс возвращаемого элемента
     * @return element [T]
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return element(index);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    /**
     * Удаляет элемент по index и возвращает удаленный element
     *
     * @param index - индекс удаляемого элемента
     * @return removed element T
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Object[] tempData = array;
        T oldElement = (T) tempData[index];
        remove(array, index);
        return oldElement;
    }

    /**
     * Очищает весь список
     */
    @Override
    public void clear() {
        final Object[] newArray = array;
        for (int to = size, i = size = 0; i < to; i++) {
            newArray[i] = null;
        }
    }

    /**
     * @return возвращает размер списка
     */
    @Override
    public int getSize() {
        return size;
    }


    /**
     * Сортирует список объектов T в порядке убывания
     */
    @Override
    public void sort() {
        Arrays.sort((T[]) array, 0, size);

    }

    /**
     * Сортирует объекты внутри списка согласно переданному компаратору
     *
     * @param comparator - компаратор, согласно которому будет отсортирован список
     */
    @Override
    public void sort(Comparator<T> comparator) {
        Arrays.sort((T[]) array, 0, size, comparator);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniList<?> uniList = (UniList<?>) o;
        return size == uniList.size && Arrays.equals(array, uniList.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
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

    private T element(int index) {
        return (T) array[index];
    }


    private void remove(Object[] inboundArray, int index) {
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(inboundArray, index + 1, inboundArray, index, newSize - index);
        inboundArray[size = newSize] = null;
    }

}
