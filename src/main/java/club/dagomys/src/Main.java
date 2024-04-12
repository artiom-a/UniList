package club.dagomys.src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UniList<Object> list = new UniList<>();

        list.add("kjckdljdlkcjldcjkdc");

        System.out.println(list.isEmpty());
        System.out.println(list.get(0));

        System.out.println("Hello world!");
    }
}