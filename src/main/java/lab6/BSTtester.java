package lab6;

import java.util.Arrays;
import java.util.Iterator;

public class BSTtester {
  public static void main(String[] args) {
    LinkedBinarySearchTree<Integer> bst = new LinkedBinarySearchTree<>();

    bst.addElement(11);
    bst.addElement(6);
    bst.addElement(8);
    bst.addElement(19);
    // bst.addElement(4);
    // bst.addElement(10);
    // bst.addElement(5);
    // bst.addElement(17);
    // bst.addElement(43);
    // bst.addElement(49);
    // bst.addElement(31);

    Iterator<Integer> inorder = bst.iteratorInOrder();
    while (inorder.hasNext()) System.out.printf("%d ", inorder.next());
    System.out.println();
    Iterator<Integer> pre = bst.iteratorPreOrder();
    while (pre.hasNext()) System.out.printf("%d ", pre.next());
    System.out.println();
    Iterator<Integer> post = bst.iteratorPostOrder();
    while (post.hasNext()) System.out.printf("%d ", post.next());
    System.out.println();
    Iterator<Integer> level = bst.iteratorLevelOrder();
    while (level.hasNext()) System.out.printf("%d ", level.next());

    System.out.println();
    System.out.println();
    bst.printTree();

    LinkedBinaryTree<Integer> bt = constructTree(new Integer[]{11, 6, 4, 5, 8, 10, 19, 17, 43, 31, 49}, new Integer[]{4, 5, 6, 8, 10, 11, 17, 19, 31
            , 43, 49});
    Iterator<Integer> btpre = bt.iteratorPreOrder();
    while (btpre.hasNext()) System.out.printf("%d ", btpre.next());
    System.out.println();
    Iterator<Integer> btinorder = bt.iteratorInOrder();
    while (btinorder.hasNext()) System.out.printf("%d ", btinorder.next());
    System.out.println();
  }

  public static <T> LinkedBinaryTree<T> constructTree(T[] preorder, T[] inorder) {
    if (preorder.length == 0) return null;
    T root = preorder[0];
    int indexOfRoot = 0;
    for (T item : inorder) {
      if (item == root) {
        break;
      }
      indexOfRoot++;
    }
    // Split inorder into left and right subtree
    T[] inorderLeft = Arrays.copyOfRange(inorder, 0, indexOfRoot);
    T[] inorderRight = Arrays.copyOfRange(inorder, indexOfRoot + 1, inorder.length);
    // Split preorder into left and right
    T[] preorderLeft = Arrays.copyOfRange(preorder, 1, 1 + inorderLeft.length);
    T[] preorderRight = Arrays.copyOfRange(preorder, 1 + inorderLeft.length, preorder.length);
    LinkedBinaryTree<T> tree = new LinkedBinaryTree<>(root);
    LinkedBinaryTree<T> left = constructTree(preorderLeft, inorderLeft);
    LinkedBinaryTree<T> right = constructTree(preorderRight, inorderRight);
    if(left != null){
      tree.root.left = left.root;
    }
    if(right != null) {
      tree.root.right = right.root;
    }
    return tree;
  }
}
