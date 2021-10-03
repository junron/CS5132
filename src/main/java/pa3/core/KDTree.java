package pa3.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KDTree {
  // You are NOT allowed to add more attributes.
  private KDTreeNode<ATM> root; //The root node of the k-d Tree
  private int numNodes; //The number of ATM objects / nodes in the k-d Tree

  // Each KDTreeNode should refer to an element of the following array of ATM objects
  private ATM[] atmArr; //An array of ATM objects read from the file put in order of the file

  // Complete the code for the methods below. You are allowed any additional helper methods.

  private boolean atm2Nearer(ATM atm1, ATM atm2, double targetX, double targetY) {
    double distance1 = Math.pow(atm1.getX() - targetX, 2) + Math.pow(atm1.getY() - targetY, 2);
    double distance2 = Math.pow(atm2.getX() - targetX, 2) + Math.pow(atm2.getY() - targetY, 2);
    if (distance2 < distance1) return true;
    if (distance2 == distance1) {
      // Compare x then y
      int res = Double.compare(atm2.getX(), atm1.getX());
      if (res < 0) return true;
      if (res > 0) return false;
      return Double.compare(atm2.getY(), atm1.getY()) < 0;
    }
    return false;
  }

  private int compareATMs(ATM atm1, ATM atm2, int depth) {
    int res;
    if (depth % 2 == 0) {
      res = Double.compare(atm1.getX(), atm2.getX());
    } else {
      res = Double.compare(atm1.getY(), atm2.getY());
    }
    return res;
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
    }
    this.rebalance();
  }

  // Method to perform node insertion. You may define your own parameters in this method
  // The insertNode method is to be utilised in the constructor
  public void insertNode(ATM atm) {
    // Add atm to array and rebalance
    this.atmArr = Arrays.copyOf(atmArr, this.numNodes + 1);
    this.atmArr[this.numNodes] = atm;
    this.rebalance();
    this.numNodes++;
  }

  // Method to perform node deletion.
  // DO NOT CHANGE THE METHOD HEADER AND PARAMETERS
  public ATM deleteNode(ATM atmdel) {
    // Remove atmDel from atmArr, then rebalance tree
    ATM[] newAtmArr = new ATM[atmArr.length - 1];
    int atmCount = 0;
    int removed = 0;
    for (ATM atm : atmArr) {
      if (atm.equals(atmdel)) {
        removed++;
        continue;
      }
      // atmDel does not exist
      // Since no atm was deleted, return null
      if (atmCount == newAtmArr.length) {
        return null;
      }
      newAtmArr[atmCount] = atm;
      atmCount++;
    }
    if (removed != 1) {
      newAtmArr = Arrays.copyOf(newAtmArr, atmArr.length - removed);
    }

    this.atmArr = newAtmArr;
    this.rebalance();
    this.numNodes--;
    return atmdel;
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
    if (compareATMs(atm, curr.getItem(), depth) < 0) {
      next = curr.getLeft();
      other = curr.getRight();
    } else {
      next = curr.getRight();
      other = curr.getLeft();
    }
    if (next != null) {
      // Explore expected side
      best = nearestNeighbourHelper(x, y, next, depth + 1);
      double bestDistance = squaredATMDistance(atm, best.getItem());
      // Current still larger
      if (!atm2Nearer(curr.getItem(), best.getItem(), x, y)) {
        best = curr;
      } else {
        // Found better
        currDist = bestDistance;
      }
    } else {
      best = curr;
    }
    // Other side has nothing to search
    if (other == null) return best;
    // Find distance to bounding box boundary
    // if this distance is greater than the current distance, discard all nodes in this bounding box
    double boundingBoxDistance = squaredATMBoundingBoxDistance(atm, curr.getItem(), depth);
    if (boundingBoxDistance > currDist) {
      return best;
    }
    KDTreeNode<ATM> otherBest = nearestNeighbourHelper(x, y, other, depth + 1);
    // Check if other side produces better result
    if (atm2Nearer(best.getItem(), otherBest.getItem(), x, y)) {
      return otherBest;
    } else {
      return best;
    }
  }

  // Method to rebalance the k-d Tree. You may implement this method in the insertNode and deleteNode methods
  public void rebalance() {
    this.root = buildTree(atmArr, 0);
  }

  private KDTreeNode<ATM> buildTree(ATM[] elements, int depth) {
    if (elements == null || elements.length == 0) return null;
    // Sort by coordinate depending on depth
    Arrays.sort(elements, (atm1, atm2) -> compareATMs(atm1, atm2, depth));

    // Set median element as root
    int medianIndex = elements.length / 2;
    // Ensures that all elements with the same key are on the same side of the tree
    while (medianIndex < elements.length - 1) {
      // Next element is the same, increment median index
      if (compareATMs(elements[medianIndex], elements[medianIndex + 1], depth) == 0) {
        medianIndex++;
      } else {
        // Next element is different, splitting is correct.
        break;
      }
    }
    ATM median = elements[medianIndex];

    KDTreeNode<ATM> root = new KDTreeNode<>(median);
    // Recursively build subtrees using remaining elements
    KDTreeNode<ATM> leftTree = buildTree(Arrays.copyOfRange(elements, 0, medianIndex), depth + 1);
    KDTreeNode<ATM> rightTree = buildTree(Arrays.copyOfRange(elements, medianIndex + 1, elements.length), depth + 1);
    root.setLeft(leftTree);
    root.setRight(rightTree);
    return root;
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

