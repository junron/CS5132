package pa3;

public class KDTreeTester {
    public static void main(String[] args) {
        System.out.println("Testing creation of k-d Tree and the In-order Traversal:");
        KDTree kdtree = new KDTree("ATMLocations.csv");
        System.out.println(kdtree);
        System.out.println(kdtree.nodesList());

        System.out.println("------------------------------");
        System.out.println("Testing the nearest neighbour method:");
        System.out.println(kdtree.nearestNeighbour(2, 10, kdtree.getRoot()));

        System.out.println("------------------------------");
        System.out.println("Testing deletion and rebalance:");
        ATM atm = new ATM("Block 781", "522781", 9, 6);
        kdtree.deleteNode(atm);
        System.out.println(kdtree);
        System.out.println("Testing the nearest neighbour method again:");
        // System.out.println(kdtree.nearestNeighbour(8, 6, kdtree.getRoot()));
        // System.out.println(kdtree.nearestNeighbour(7, 4, kdtree.getRoot()));
    }
}
