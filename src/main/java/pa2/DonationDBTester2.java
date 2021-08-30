package pa2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester2 {
  public static void main(String[] args) {
    int n = 305;
    int k = 13;
    for (int i = n; i < n + 100; i++) {
      System.out.println(i);
      DonationDB db = new DonationDB("testcases/" + i + ".txt");
      int s = db.allocations(k).size();
      // File file = new File("testcases/" + i + ".txt");
      // Scanner scanner;
      // try {
      //   scanner = new Scanner(file);
      // } catch (FileNotFoundException e) {
      //   throw new IllegalArgumentException("File could not be found!");
      // }
      //
      // // Read number of items
      // int numItems = Integer.parseInt(scanner.nextLine());
      // // Read item names and values
      // for (int j = 0; j < numItems; j++) {
      //   String[] line = scanner.nextLine().split(",");
      // }
      System.out.println(s);
    }
  }
}
