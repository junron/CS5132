package pa3.core;

public class KDTreeNode<T> {
  // You are NOT allowed to add more attributes
  private T item;
  private KDTreeNode<T> left;
  private KDTreeNode<T> right;

  // Complete the constructor for the KDTreeNode
  public KDTreeNode(T item) {
    this.item = item;
  }

  // You will require mutators for left and right. Code the mutators.
  public void setLeft(KDTreeNode<T> left) {
    this.left = left;
  }

  public void setRight(KDTreeNode<T> right) {
    this.right = right;
  }


  // You will require accessors for item, left and right. Code the accessors
  public T getItem() {
    return item;
  }

  public KDTreeNode<T> getLeft() {
    return left;
  }

  public KDTreeNode<T> getRight() {
    return right;
  }

  //DO NOT EDIT the following toString() method
  @Override
  public String toString() {
    return item.toString();
  }
}
