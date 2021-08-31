package lab6;

import java.util.*;

public class BSTTester {
  public static void main(String[] args) {
    LinkedBinarySearchTree<Integer> BST = new LinkedBinarySearchTree<Integer>();
    BST.addElement(11);
    BST.addElement(6);
    BST.addElement(8);
    BST.addElement(19);
    BST.addElement(4);
    BST.addElement(10);
    BST.addElement(5);
    BST.addElement(17);
    BST.addElement(43);
    BST.addElement(49);
    BST.addElement(31);
    //For Coursemology Q1
    System.out.println(inOrder(BST.root, new ArrayList<Integer>()));
    System.out.println(preOrder(BST.root, new ArrayList<Integer>()));
    System.out.println(postOrder(BST.root, new ArrayList<Integer>()));
    System.out.println(levelOrder(BST));
    // For Coursemology Q2
    System.out.println(BST);

    // For Coursemology Q3
    ArrayList<Integer> preOrder = new ArrayList<Integer>(Arrays.asList(11, 6, 4, 5, 8, 10, 19, 17, 43, 31, 49));
    ArrayList<Integer> inOrder = new ArrayList<Integer>(Arrays.asList(4, 5, 6, 8, 10, 11, 17, 19, 31, 43, 49));
    System.out.println(constructTree(preOrder, inOrder));
  }

  // Q1: Complete the inOrder Traversal here
  public static ArrayList<Integer> inOrder(BinaryTreeNode<Integer> curr, ArrayList<Integer> result) {
    if (curr == null) return result;
    ArrayList<Integer> out = result;
    out = inOrder(curr.left, out);
    out.add(curr.element);
    out = inOrder(curr.right, out);
    return out;
  }

  // Q1: Complete the preOrder Traversal here
  public static ArrayList<Integer> preOrder(BinaryTreeNode<Integer> curr, ArrayList<Integer> result) {
    if (curr == null) return result;
    ArrayList<Integer> out = result;
    out.add(curr.element);
    out = preOrder(curr.left, out);
    out = preOrder(curr.right, out);
    return out;
  }

  // Q1: Complete the postOrder Traversal here
  public static ArrayList<Integer> postOrder(BinaryTreeNode<Integer> curr, ArrayList<Integer> result) {
    if (curr == null) return result;
    ArrayList<Integer> out = result;
    out = postOrder(curr.left, out);
    out = postOrder(curr.right, out);
    out.add(curr.element);
    return out;
  }

  // Q1: Complete an iterative levelOrder Traversal here
  public static ArrayList<Integer> levelOrder(LinkedBinarySearchTree<Integer> BST) {
    ArrayList<Integer> out = new ArrayList<>();
    ArrayDeque<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
    if (BST == null) return out;
    queue.add(BST.root);
    while (!queue.isEmpty()) {
      BinaryTreeNode<Integer> curr = queue.poll();
      out.add(curr.element);
      if (curr.left != null) queue.add(curr.left);
      if (curr.right != null) queue.add(curr.right);
    }
    return out;
  }

  // Q3: Complete the following method to construct a tree from the pre and in order traversals
  // You may add your own auxillary methods
  public static LinkedBinarySearchTree<Integer> constructTree(ArrayList<Integer> preOrder, ArrayList<Integer> inOrder) {
    if (preOrder.size() == 0) return null;
    LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>(preOrder.get(0));
    BinaryTreeNode<Integer> root = tree.root;

    int i = 0;
    // Split inorder into left and right subtree
    for (int element : inOrder) {
      if (element == root.element) {
        break;
      }
      i++;
    }

    ArrayList<Integer> inorderLeft = new ArrayList<>(inOrder.subList(0, i));
    ArrayList<Integer> inorderRight = new ArrayList<>(inOrder.subList(i + 1, inOrder.size()));

    ArrayList<Integer> preorderLeft = new ArrayList<>(preOrder.subList(1, i + 1));
    ArrayList<Integer> preorderRight = new ArrayList<>(preOrder.subList(i + 1, preOrder.size()));

    LinkedBinarySearchTree<Integer> leftTree = constructTree(preorderLeft, inorderLeft);
    LinkedBinarySearchTree<Integer> rightTree = constructTree(preorderRight, inorderRight);
    root.left = leftTree != null ? leftTree.root : null;
    root.right = rightTree != null ? rightTree.root : null;

    return tree;
  }

}
