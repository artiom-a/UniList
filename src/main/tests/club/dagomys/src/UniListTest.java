package club.dagomys.src;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UniListTest {
    private static UniList<Object> list;

    @BeforeAll
    public static void init() {
        list = new UniList<>();
    }

    @Test
    @DisplayName("Добавить в лист")
    public void add() {
        assertTrue(list.add(new Object()));
        Stream.generate(Object::new).limit(10000).forEach(list::add);
        assertEquals(10001, list.getSize());

    }

    ;

    @Test
    @DisplayName("Добавление по индексу в лист")
    public void addToIndex() {
        String neString = new String();
        list.add(10, neString);
        assertEquals(list.get(10), neString);
        assertEquals(10002, list.getSize());
    }


    @Test
    @DisplayName("Получение по индексу")
    public void get() {
        assertInstanceOf(Object.class, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(100000));
    }

    @Test
    @DisplayName("Удаление элемента по индексу")
    public void remove() {
//        Stream.of(list).limit(100).forEach(objectUniList -> ob);

    }

    @Test
    @DisplayName("Удаление всех элементов листа")
    public void clear() {
        UniList<Object> sList = new UniList<>();
        Stream.generate(Object::new).limit(10000).forEach(sList::add);
        assertFalse(sList.isEmpty());
        sList.clear();
        System.out.println(sList.isEmpty());
        System.out.println(sList.get(0));
        assertTrue(sList.isEmpty());
    }

    @Test
    @DisplayName("Сортировка")
    public void sort() {

    }

    @Test
    @DisplayName("Сортировка через переданный компаратор")
    public void sortByComparator() {

    }

    @Test
    @DisplayName("Проверка метода isEmpty")
    public void isEmpty() {
        UniList<Object> sList = new UniList<>();
        assertTrue(sList.isEmpty());
        assertFalse(list.isEmpty());
    }
}
