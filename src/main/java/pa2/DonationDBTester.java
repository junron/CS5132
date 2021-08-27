package pa2;

// DO NOT EDIT THE CLASS!
// Class to test the implementation of the DonationDB
public class DonationDBTester {
  public static void main(String[] args) {
    DonationDB db = new DonationDB("items.txt");
    System.out.println(db.allocations(3));
    System.out.println(db.numChecks);
  }
}
