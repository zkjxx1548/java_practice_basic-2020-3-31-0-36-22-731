package option;

import java.util.List;

public class Tool {
    public static <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }
}
