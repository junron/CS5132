package lab5;

import java.awt.*;

public class Q3 {
  public static void main(String[] args) {
    draw(1, 0);
  }

  public static void draw(double width, double offset) {
    StdDraw.filledCircle(offset + width / 2, 0.5, width / 2);
    StdDraw.setPenColor(Color.WHITE);
    StdDraw.filledCircle(offset + width / 4, 0.5, width / 4);
    StdDraw.filledCircle(offset + 3 * width / 4, 0.5, width / 4);
    StdDraw.setPenColor(Color.BLACK);
    if (width < 0.01) {
      return;
    }
    draw(width / 4, offset);
    draw(width / 4, offset + width / 4);
    draw(width / 4, offset + width / 2);
    draw(width / 4, offset + 3 * width / 4);
  }
}
