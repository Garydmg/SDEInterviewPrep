package implementation;

import java.util.List;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(0);
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}
