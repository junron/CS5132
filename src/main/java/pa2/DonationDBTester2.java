package pa2;

import java.util.ArrayList;
import java.util.Random;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester2 {
  static Random random = new Random();

  public static void main(String[] args) {
    int n = 100;
    int k = 6;
    for (int i = n; i < n+100; i++) {
      DonationDB db = new DonationDB("testcases/" + i + ".txt");
      db.allocations(k);
      System.out.println(i);
      System.out.println("c"+db.numChecks);

    }
  }
}
