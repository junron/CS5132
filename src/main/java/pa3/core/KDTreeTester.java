package pa3.core;

public class KDTreeTester {
  public static void main(String[] args) {
    System.out.println("Testing creation of k-d Tree and the In-order Traversal:");
    KDTree kdtree = new KDTree("ATMLocations.csv");
    System.out.println(kdtree);
    System.out.println(kdtree.nodesList());

    System.out.println("------------------------------");
    System.out.println("Testing the nearest neighbour method:");
    System.out.println(kdtree.nearestNeighbour(8, 7, kdtree.getRoot()));

    System.out.println("------------------------------");
    System.out.println("Testing deletion and rebalance:");
    ATM atm = new ATM("Block 781", "522781", 9, 6);
    kdtree.deleteNode(atm);
    System.out.println(kdtree);
    System.out.println("Testing the nearest neighbour method again:");
    System.out.println(kdtree.nearestNeighbour(8, 7, kdtree.getRoot()));
  }
}
