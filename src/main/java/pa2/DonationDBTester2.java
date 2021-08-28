package pa2;

import java.util.ArrayList;
import java.util.Random;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester2 {
  public static void main(String[] args) {
    int n = 0;
    int k = 3;
    for (int i = n; i < n + 100; i++) {
      System.out.println(i);
      DonationDB db = new DonationDB("testcases/" + i + ".txt");
      System.out.println(db.allocations(k).size());
    }
  }
}
