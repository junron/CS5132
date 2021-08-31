package lab6;

import java.util.ArrayList;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
  protected int count;
  protected BinaryTreeNode<T> root;

  /**
   * Creates an empty binary tree.
   */
  public LinkedBinaryTree() {
    count = 0;
    root = null;
  }

  /**
   * Creates a binary tree with the specified element as its root.
   *
   * @param element the element that will become the root of the new binary
   *                tree
   */
  public LinkedBinaryTree(T element) {
    count = 1;
    root = new BinaryTreeNode<T>(element);
  }

  /**
   * Returns a reference to the element at the root
   *
   * @return a reference to the specified target
   * @throws EmptyCollectionException if the tree is empty
   */
  public T getRoot() throws EmptyCollectionException {
    if (root == null) throw new EmptyCollectionException("binary tree");

    return (root.element);
  }

  /**
   * Returns true if this binary tree is empty and false otherwise.
   *
   * @return true if this binary tree is empty
   */
  public boolean isEmpty() {
    return (count == 0);
  }

  /**
   * Returns the integer size of this tree.
   *
   * @return the integer size of this tree
   */
  public int size() {
    return count;
  }

  /**
   * Returns true if this tree contains an element that matches the
   * specified target element and false otherwise.
   *
   * @param targetElement the element being sought in this tree
   * @return true if the element in is this tree
   * @throws ElementNotFoundException if an element not found exception occurs
   */
  public boolean contains(T targetElement) {
    T temp;
    boolean found = false;

    try {
      temp = find(targetElement);
      found = true;
    } catch (Exception ElementNotFoundException) {
      found = false;
    }

    return found;
  }

  /**
   * Returns a reference to the specified target element if it is
   * found in this binary tree.  Throws a NoSuchElementException if
   * the specified target element is not found in the binary tree.
   *
   * @param targetElement the element being sought in this tree
   * @return a reference to the specified target
   * @throws ElementNotFoundException if an element not found exception occurs
   */
  public T find(T targetElement) throws ElementNotFoundException {
    BinaryTreeNode<T> current = findAgain(targetElement, root);

    if (current == null) throw new ElementNotFoundException("binary tree");

    return (current.element);
  }

  /**
   * Returns a reference to the specified target element if it is
   * found in this binary tree.
   *
   * @param targetElement the element being sought in this tree
   * @param next          the element to begin searching from
   */
  private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
    if (next == null) return null;

    if (next.element.equals(targetElement)) return next;

    BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

    if (temp == null) temp = findAgain(targetElement, next.right);

    return temp;
  }

  public String toString() {
    String out = "";
    ArrayList<String> rows = new ArrayList<>();
    toStringHelper(root, 0, rows);
    for (String row : rows) {
      if (row.isBlank()) continue;
      if (!out.isEmpty()) out += "\n";
      out += row;
    }
    return out + "\n";
  }

  private void toStringHelper(BinaryTreeNode<T> root, int depth, ArrayList<String> out) {
    if (root == null) {
      if (out.size() <= depth) {
        out.add("   ");
      } else {
        out.set(depth, out.get(depth) + "   ");
      }
      return;
    }

    if (out.size() <= depth) {
      out.add(String.format("%-2d", root.element) + " ");
    } else {
      out.set(depth, out.get(depth) + String.format("%-2d", root.element) + " ");
    }
    toStringHelper(root.left, depth + 1, out);
    toStringHelper(root.right, depth + 1, out);
  }
}

