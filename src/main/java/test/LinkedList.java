package test;

public class LinkedList<T> {
  public Node<T> head;

  public void insert(T elem) {
    if (isEmpty()) {
      head = new Node<>(elem);
      return;
    }
    Node<T> tail = getTail();
    tail.next = new Node<>(elem);
  }

  public Node<T> getTail() {
    if (isEmpty()) {
      return null;
    }
    Node<T> tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    return tail;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return null;
    }
    String out = "";
    Node<T> current = head;
    while (current != null) {
      out += current.element.toString() + " -> ";
      current = current.next;
    }
    return out + "null";
  }

  public boolean isEmpty() {
    return head == null;
  }

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.insert(1);
    list.insert(2);
    list.insert(3);
    System.out.println(list);
  }
}

class Node<T> {
  public T element;
  public Node<T> next;

  Node(T element) {
    this.element = element;
  }

  Node(T element, Node<T> next) {
    this.element = element;
    this.next = next;
  }
}
