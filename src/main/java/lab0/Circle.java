package lab0;

public class Circle extends GeometricObject {
  private double radius;

  /**
   * Default constructor
   */
  public Circle() {
    this(1.0);
  }


  /**
   * Construct circle with a specified radius
   */
  public Circle(double radius) {
    this(radius, "white", false);
  }

  /**
   * Construct a circle with specified radius, filled, and color
   */
  public Circle(double radius, String color, boolean filled) {
    super(color, filled);
    this.radius = radius;
  }

  /**
   * Return radius
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Set a new radius
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /**
   * Override the equals() method defined in the Object class
   */
  public boolean equals(Circle circle) {
    return this.radius == circle.getRadius();
  }

  @Override
  public String toString() {
    return "[Circle] radius = " + radius;
  }


  @Override
  public double getArea() {
    return Math.PI * Math.pow(this.getRadius(), 2);
  }

  @Override
  public double getPerimeter() {
    return 2.0 * Math.PI * this.getRadius();
  }

}
