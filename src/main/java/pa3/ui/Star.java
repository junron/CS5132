package pa3.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


// Adapted from http://java-sl.com/shapes.html
public class Star extends Polygon {
  public Star(double x, double y, int r, int innerR) {
    double[] xCoords = getXCoordinates(x, y, r, innerR);
    double[] yCoords = getYCoordinates(x, y, r, innerR);
    for (int i = 0; i < xCoords.length; i++) {
      getPoints().addAll(xCoords[i], yCoords[i]);
    }
    setFill(Color.RED);
    setRotate(-15);
  }

  protected static double[] getXCoordinates(double x, double y, int r, int innerR) {
    double[] res = new double[10];
    double addAngle = 2 * Math.PI / 5;
    double angle = 0;
    double innerAngle = (double) 0 + Math.PI / 5;
    for (int i = 0; i < 5; i++) {
      res[i * 2] = r * Math.cos(angle) + x;
      angle += addAngle;
      res[i * 2 + 1] = innerR * Math.cos(innerAngle) + x;
      innerAngle += addAngle;
    }
    return res;
  }

  protected static double[] getYCoordinates(double x, double y, int r, int innerR) {
    double[] res = new double[10];
    double addAngle = 2 * Math.PI / 5;
    double angle = 0;
    double innerAngle = (double) 0 + Math.PI / 5;
    for (int i = 0; i < 5; i++) {
      res[i * 2] = r * Math.sin(angle) + y;
      angle += addAngle;
      res[i * 2 + 1] = innerR * Math.sin(innerAngle) + y;
      innerAngle += addAngle;
    }
    return res;
  }
}
