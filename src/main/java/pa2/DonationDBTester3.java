package pa2;

import java.util.ArrayList;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester3 {

  public static void main(String[] args) {
    int numItems = 3;
    int families = 2;
    int max = 5;
    for (int i = 0; i < max; i++) {
      for (int j = 0; j < max; j++) {
        for (int k = 0; k < max; k++) {
          ArrayList<Item> items = new ArrayList<>();
          items.add(new Item("A", i));
          items.add(new Item("B", j));
          items.add(new Item("C", k));
          DonationDB db = new DonationDB(items);
          ArrayList<ArrayList<Item>> res = db.allocations(families);
          if (db.numChecks > 1) {
            System.out.printf("%d %d %d\n", i, j, k);
            System.out.println(db.numChecks);
            if (res.size() > 0) {
              System.out.println("Ok");
              // System.out.println(res);
            }
          }
        }
      }
    }
  }
}
