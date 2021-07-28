package lab0;

// Tester class for your testing
public class TestCircle {
  // Main method
  public static void main(String[] args) {
    // Create two comarable circle
    ComparableCircle circle1 = new ComparableCircle(15.001);
    ComparableCircle circle2 = new ComparableCircle(15);
    System.out.println(circle1.getArea());
    System.out.println(circle2.getArea());

    // Display the max circle
    ComparableCircle circle3 = (ComparableCircle) Max.max(circle1, circle2);
    System.out.println("The max circle's radius is " + circle3.getRadius());
    System.out.println(circle3);
  }
}

