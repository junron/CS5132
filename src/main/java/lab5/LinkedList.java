package lab5;

public class LinkedList {


  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(4);
    head.next.next.next = new Node(5);
    head = insertInOrder(0, head);
    while (head != null) {
      System.out.println(head.data);
      head = head.next;
    }
  }

  public static Node insertInOrder(int k, Node p) {
    Node n = new Node(k);
    if (p == null) {
      return n;
    }
    if (p.data > k) {
      n.next = p;
      return n;
    }
    p.next = insertInOrder(k, p.next);
    return p;
  }

}

class Node {
  Node next;
  int data;

  Node(int d) {
    this.data = d;
  }
}
