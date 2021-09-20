package pa3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class KDTree {
  // You are NOT allowed to add more attributes.
  private KDTreeNode<ATM> root; //The root node of the k-d Tree
  private int numNodes; //The number of ATM objects / nodes in the k-d Tree

  // Each KDTreeNode should refer to an element of the following array of ATM objects
  private ATM[] atmArr; //An array of ATM objects read from the file put in order of the file

  // Complete the code for the methods below. You are allowed any additional helper methods.

  private int compareATMs(ATM atm1, ATM atm2, int depth) {
    if (depth % 2 == 0) {
      if (atm1.getX() < atm2.getX()) {
        return -1;
      } else if (atm1.getX() == atm2.getX()) {
        return 0;
      }
    } else {
      if (atm1.getY() < atm2.getY()) {
        return -1;
      } else if (atm1.getY() == atm2.getY()) {
        return 0;
      }
    }
    return 1;
  }

  private double squaredATMDistance(ATM atm1, ATM atm2) {
    return Math.pow(atm1.getX() - atm2.getX(), 2) + Math.pow(atm1.getY() - atm2.getY(), 2);
  }

  private double squaredATMBoundingBoxDistance(ATM atm1, ATM atm2, int depth) {
    double coord1 = depth % 2 == 0 ? atm1.getX() : atm1.getY();
    double coord2 = depth % 2 == 0 ? atm2.getX() : atm2.getY();
    return (coord1 - coord2) * (coord1 - coord2);
  }

  // Constructor for the KDTree. See the KDTreeTester for how the constructor should work
  public KDTree(String filename) {
    File file = new File(filename);
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found!");
    }
    this.numNodes = Integer.parseInt(scanner.nextLine());
    this.atmArr = new ATM[this.numNodes];

    for (int i = 0; i < this.numNodes; i++) {
      String input = scanner.nextLine();
      String[] parts = input.split(",");
      String location = parts[0];
      String postal = parts[1];
      double x = Double.parseDouble(parts[2]);
      double y = Double.parseDouble(parts[3]);
      ATM atm = new ATM(location, postal, x, y);
      this.atmArr[i] = atm;
      insertNode(atm);
    }
  }

  // Method to perform node insertion. You may define your own parameters in this method
  // The insertNode method is to be utilised in the constructor
  public void insertNode(ATM atm) {
    if (root == null) {
      root = new KDTreeNode<>(atm);
      return;
    }
    KDTreeNode<ATM> curr = root;

    int depth = 0;
    while (true) {
      KDTreeNode<ATM> next;
      // ATM is smaller, go left
      if (compareATMs(atm, curr.getItem(), depth) == -1) {
        next = curr.getLeft();
      } else {
        next = curr.getRight();
      }
      if (next == null) break;
      curr = next;
      depth++;
    }

    // ATM is smaller, go left
    if (compareATMs(atm, curr.getItem(), depth) == -1) {
      curr.setLeft(new KDTreeNode<>(atm));
    } else {
      curr.setRight(new KDTreeNode<>(atm));
    }
  }

  // Method to perform node deletion.
  // DO NOT CHANGE THE METHOD HEADER AND PARAMETERS
  public ATM deleteNode(ATM atmdel) {
    KDTreeNode<ATM> curr = root;
    int depth = 0;
    while (true) {
      KDTreeNode<ATM> next;
      // ATM is smaller, go left
      if (compareATMs(atmdel, curr.getItem(), depth) == -1) {
        next = curr.getLeft();
        // Next is the node to be deleted, delete child of current
        if (next.getItem().equals(atmdel)) {
          curr.setLeft(null);
          // Reinsert children
          insertNodeChildren(next);
          return atmdel;
        }
      } else {
        next = curr.getRight();
        // Next is the node to be deleted, delete child of current
        if (next.getItem().equals(atmdel)) {
          curr.setRight(null);
          // Reinsert children
          insertNodeChildren(next);
          return atmdel;
        }
      }
      curr = next;
      depth++;
    }
  }

  private void insertNodeChildren(KDTreeNode<ATM> node) {
    insertNode(node.getLeft());
    insertNode(node.getRight());
  }

  private void insertNode(KDTreeNode<ATM> node) {
    if (node == null) return;
    insertNode(node.getItem());
    insertNode(node.getLeft());
    insertNode(node.getRight());
  }

  // Method does a NON-RECURSIVE in-order traveral of the k-d Tree
  // DO NOT CHANGE THE METHOD HEADER AND PARAMETERS
  public ArrayList<ATM> nodesList() {
    ArrayList<ATM> out = new ArrayList<>();
    Stack<KDTreeNode<ATM>> stack = new Stack<>();
    KDTreeNode<ATM> curr = root;
    while (curr != null || !stack.isEmpty()) {
      // Go all the way down left
      while (curr != null) {
        stack.push(curr);
        curr = curr.getLeft();
      }
      // Go up one level and traverse right
      curr = stack.pop();
      out.add(curr.getItem());
      curr = curr.getRight();
    }

    return out;
  }

  // Method to perform the nearest neighbour search in the k-d Tree.
  // You may add on your own parameters in this method. Ensure you do the same for the tester if so
  public ATM nearestNeighbour(double x, double y, KDTreeNode<ATM> curr) {
    return nearestNeighbourHelper(x, y, curr, 0).getItem();
  }

  private KDTreeNode<ATM> nearestNeighbourHelper(double x, double y, KDTreeNode<ATM> curr, int depth) {
    ATM atm = new ATM("", "", x, y);
    double currDist = squaredATMDistance(atm, curr.getItem());
    // Traverse all the way down like in insertion
    KDTreeNode<ATM> next, other, best;
    if (compareATMs(atm, curr.getItem(), depth) == -1) {
      next = curr.getLeft();
      other = curr.getRight();
    } else {
      next = curr.getRight();
      other = curr.getLeft();
    }
    if (next != null) {
      best = nearestNeighbourHelper(x, y, next, depth + 1);
      double bestDistance = squaredATMDistance(atm, best.getItem());
      // Current still larger
      if (bestDistance > currDist) {
        best = curr;
      } else {
        // Found better
        currDist = bestDistance;
        curr = best;
      }
    } else {
      best = curr;
    }
    // Other side has nothing to search
    if (other == null) return best;
    // Find distance to bounding box boundary
    // if this distance is greater than the current distance, discard all nodes in this bounding box
    double boundingBoxDistance = squaredATMBoundingBoxDistance(atm, other.getItem(), depth);
    if (boundingBoxDistance > currDist) {
      return best;
    }
    best = nearestNeighbourHelper(x, y, other, depth + 1);
    // Check if other side produces better result
    if (squaredATMDistance(atm, best.getItem()) < currDist) {
      return best;
    } else {
      return curr;
    }
  }

  // Method to rebalance the k-d Tree. You may implement this method in the insertNode and deleteNode methods
  public void rebalance() {

  }

  //DO NOT EDIT! This method returns the root node of the tree
  public KDTreeNode<ATM> getRoot() {
    return root;
  }

  //DO NOT EDIT! This method returns the array of ATM objects
  public ATM[] getAtmArr() {
    return atmArr;
  }

  //DO NOT EDIT! This method returns the index
  //public int findATM() { return atmArr; }

  //DO NOT EDIT! This method returns a visualisation of k-d tree structure
  //Note that depending on your data, it may not show all levels. If that is the case, you can edit the number
  //levels it will show. BUT, note that Coursemology will reference the original toString() method in the template.
  @Override
  public String toString() {
    if (root == null) return "No tree present";

    int levels = (int) Math.ceil(Math.log(numNodes) / Math.log(2));
    String kdTreeStr = "";
    ArrayList<ArrayList<KDTreeNode<ATM>>> nodesStore = new ArrayList<>();
    ArrayList<KDTreeNode<ATM>> levelNodes = new ArrayList<>();

    for (int k = levels; k > 1; k--) kdTreeStr += "      ";
    kdTreeStr += "(" + root.getItem().getX() + "," + root.getItem().getY() + ")\n";
    levelNodes.add(root);
    nodesStore.add(levelNodes);

    for (int i = 1; i < levels; i++) {
      levelNodes = new ArrayList<>();
      for (int k = levels - i; k > 1; k--) kdTreeStr += "      ";
      for (KDTreeNode<ATM> n : nodesStore.get(i - 1)) {
        if (n == null) {
          levelNodes.add(null);
          kdTreeStr += "None   ";
        } else {
          levelNodes.add(n.getLeft());
          if (n.getLeft() == null) kdTreeStr += "None   ";
          else kdTreeStr += "(" + n.getLeft().getItem().getX() + "," + n.getLeft().getItem().getY() + ")  ";
          levelNodes.add(n.getRight());
          if (n.getRight() == null) kdTreeStr += "None   ";
          else kdTreeStr += "(" + n.getRight().getItem().getX() + "," + n.getRight().getItem().getY() + ")  ";
        }
      }
      nodesStore.add(levelNodes);
      kdTreeStr += "\n";
    }
    return kdTreeStr;
  }
}

