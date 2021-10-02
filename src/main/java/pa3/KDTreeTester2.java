package pa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KDTreeTester2 {
  public static void main(String[] args) throws IOException {
    long build = System.currentTimeMillis();
    KDTree kdtree = new KDTree("ATMLocations.csv");
    Scanner scanner = new Scanner(new File("target-locations.csv"));
    FileWriter scanner2 = new FileWriter(new File("output.txt"));
    int numLocations = Integer.parseInt(scanner.nextLine());

    long start = System.currentTimeMillis();
    System.out.println("Build took " + (start - build) + " milliseconds");
    for (int i = 0; i < numLocations; i++) {
      String input = scanner.nextLine();
      String[] parts = input.split(",");
      double x = Double.parseDouble(parts[2]);
      double y = Double.parseDouble(parts[3]);
      ATM res = kdtree.nearestNeighbour(x, y, kdtree.getRoot());
      String resStr = res.getX() + "," + res.getY();
      // kdtree.deleteNode(res);
      // String expected = scanner2.nextLine();
      // if (!resStr.equals(expected)) {
      //   throw new AssertionError(resStr + " != " + expected);
      // }
      scanner2.write(resStr + "\n");
      // System.out.println(resStr);
    }
    long end = System.currentTimeMillis();
    System.out.println("Operation took " + (end - start) + " milliseconds");
  }
}
