package club.dagomys.src;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

public interface CustomList<T> {


    boolean add(T element);
    boolean add(int index, T element);

    T get(int index);
    T remove(int index);

    void clear();

    T sort();

    T sort(Comparator<T> comparator);

    boolean isEmpty();

    int getSize();


}
