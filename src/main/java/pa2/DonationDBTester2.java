package pa2;

import java.util.ArrayList;
import java.util.Random;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester2 {
  static Random random = new Random();

  public static void main(String[] args) {
    int numItems = 10;
    int families = 3;
    int runs = 100;
    double expected = numItems * Math.pow(2, numItems);
    // int expected = (int) (Math.pow(2, numItems + families));
    System.out.println(expected);
    for (int j = 0; j < runs; j++) {
      ArrayList<Item> items = new ArrayList<>();
      String sex = "====================\n";
      for (int i = 0; i < numItems; i++) {
        items.add(new Item("A", random.nextInt(10)));
        sex += ("A," + items.get(items.size() - 1).getValue()) + "\n";
      }
      sex += "====================\n";
      DonationDB db = new DonationDB(items);
      ArrayList<ArrayList<Item>> res = db.allocations(families);
      if (db.numChecks >= expected ) {
        System.out.println(sex);
        System.out.println(db.numChecks);
        if (res.size() > 0) {
          System.out.println("Ok");
          // System.out.println(res);
        }
      }
    }
  }
}
