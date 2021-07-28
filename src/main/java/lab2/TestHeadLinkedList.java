package lab2;


public class TestHeadLinkedList {
  public static void main(String[] args) {
    HeadLinkedList<String> ll = new HeadLinkedList<>();
    ll.addToRear("a");
    ll.addToRear("b");
    ll.addToRear("c");
    ll.addToRear("d");
    ll.reverse();
    System.out.println(ll);
    System.out.println(ll.toString().equals("dcba"));

    HeadLinkedList<String> ll2 = new HeadLinkedList<>();
    ll2.addToRear("1");
    ll2.addToRear("3");
    ll2.addToRear("2");
    ll2.addToRear("8");
    HeadLinkedList<String> ll2b = ll2.frontBackSplit();
    System.out.println(ll2);
    System.out.println(ll2b);
    System.out.println(ll2.toString().equals("28"));
    System.out.println(ll2b.toString().equals("13"));
    
    HeadLinkedList<String> ll3 = new HeadLinkedList<>();
    ll3.addToRear("1");
    ll3.addToRear("3");
    ll3.addToRear("2");
    ll3.addToRear("8");
    ll3.addToRear("9");
    HeadLinkedList<String> ll3b = ll3.frontBackSplit();
    System.out.println(ll3);
    System.out.println(ll3b);
    System.out.println(ll3.toString().equals("89"));
    System.out.println(ll3b.toString().equals("132"));
  }
}
