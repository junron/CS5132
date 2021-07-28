package lab2;

import java.util.Iterator;

public class TestLinkedList {
  public static void main(String[] args) {
    LinkedList<String> ll = new LinkedList<>();
    ll.addToRear("aaa");
    ll.addToRear("bbb");
    ll.addToRear("ccc");

    Iterator<String>l = ll.iterator();
    while (l.hasNext()) System.out.println(l.next());
  }
}
