package club.dagomys.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UniListTest {
    private UniList<Object> list;
    private Random random;

    @BeforeEach
    public void init() {
        list = new UniList<>();
        random = new Random();
    }

    @Test
    @DisplayName("Добавить в лист")
    public void add() {
        assertTrue(list.add(new Object()));
        Stream.generate(Object::new).limit(10000).forEach(list::add);
        assertEquals(10001, list.getSize());

    }

    @Test
    @DisplayName("Добавление по индексу в лист")
    public void addToIndex() {
        Stream.generate(Object::new).limit(10).forEach(list::add);
        list.add(4, "new Object()");
        assertInstanceOf(String.class, list.get(4));
        assertEquals(11, list.getSize());
    }


    @Test
    @DisplayName("Получение по индексу")
    public void get() {
        assertTrue(list.add("new Object()"));
        assertInstanceOf(String.class, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100000));
    }

    @Test
    @DisplayName("Удаление элемента по индексу")
    public void remove() {
        list.add(new Object());
        assertInstanceOf(Object.class, list.remove(0));
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Удаление всех элементов листа")
    public void clear() {
        Stream.generate(Object::new).limit(10000).forEach(list::add);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Сортировка")
    public void sort() {

        Stream.generate(random::nextInt).limit(10000).forEach(list::add);
//        Stream.generate(()->(char) ('a' + random.nextInt(26))).limit(10000).forEach(list::add);
        list.sort();
        for (int i = 0; i < list.getSize(); i++) {
            assertTrue((Integer) list.get(i) <= (Integer) list.get(++i));
//            assertTrue((Character) list.get(i) <= (Character) list.get(++i));
        }
    }

    @Test
    @DisplayName("Сортировка через переданный компаратор")
    public void sortByComparator() {
        UniList<Integer> newList = new UniList<>();
        Stream.generate(random::nextInt).limit(10000).forEach(newList::add);

        Comparator<Integer> comparator = Comparator.naturalOrder();
        UniListQuickSort<Integer> sort = new UniListQuickSort<>(newList, comparator);
        sort.quickSort();
        for (int i = 0; i < newList.getSize(); i++) {
            assertTrue(newList.get(i) <= newList.get(++i));
        }

    }

    @Test
    @DisplayName("Проверка метода isEmpty")
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(new Object());
        assertFalse(list.isEmpty());

    }
}
