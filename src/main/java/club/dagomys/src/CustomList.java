package club.dagomys.src;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * Интерфейс описывающий основные методы для CustomList (аналог ArrayList<>)
 *
 * @param <T>
 */
public interface CustomList<T> {

    /**
     * Метод добавляет element в конец списка;
     *
     * @param element добавляемый элемент
     * @return возвращает true;
     */
    boolean add(T element);

    /**
     * Метод добавляет элемент в позицию index
     *
     * @param index   - позиция для добавления
     * @param element - добавляемый элемент
     * @return возвращает true;
     */
    boolean add(int index, T element);

    void set(T element, int index);

    /**
     * Метод получения элемента по индексу
     *
     * @param index - индекс возвращаемого элемента
     * @return возвращает T
     */
    T get(int index);

    /**
     * Метод удаления элемента по индексу
     *
     * @param index - индекс удаляемого элемента
     * @return возвращает удаленный элемент
     */
    T remove(int index);

    /**
     * Метод очищающий весь список. После вызова метода у списка, size=0;
     */
    void clear();

    /**
     * Метоод позваляет отсортировать список
     */
    void sort();

    /**
     * Метод сортирует список согласно переданному компаратору
     *
     * @param comparator - компаратор, согласно которому будет отсортирован список
     */
    void sort(Comparator<T> comparator);

    /**
     * Метод возвращает true если список пуст, иначе возвращает false
     *
     * @return true - список пуст
     * false - в списке есть хотя бы один элемент
     */
    boolean isEmpty();

    /**
     * @return возвращает количество элементов в списке
     */
    int getSize();


}
