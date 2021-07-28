package lab0;

public class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
  
  public ComparableCircle(double radius) {
    super(radius);
  }

  @Override
  public int compareTo(ComparableCircle o) {
    double difference = this.getArea() - o.getArea();
    if (difference < 0) {
      return -1;
    }
    if (difference > 0) {
      return 1;
    }
    return 0;
  }
}
