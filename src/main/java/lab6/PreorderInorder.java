package lab6;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

// Preorder head is root
// find left and right from 
// inorder: left root right
// preorder root left right
public class PreorderInorder {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0 || inorder.length == 0) return null;
    TreeNode root = new TreeNode(preorder[0]);
    if (preorder.length == 1 || inorder.length == 1) return root;
    // if(preorder.length == 1) return root;
    int leftIndex = 0;
    // find root in inorder. Divides into left and right
    for (int i : inorder) {
      if (i == preorder[0]) break;
      leftIndex++;
    }
    int[] leftInorder = Arrays.copyOfRange(inorder, 0, leftIndex);
    int[] rightInorder = Arrays.copyOfRange(inorder, leftIndex + 1, inorder.length);

    int[] leftPreorder = Arrays.copyOfRange(preorder, 1, leftIndex+1);
    int[] rightPreorder = Arrays.copyOfRange(preorder, leftIndex + 1, preorder.length);
    root.left = buildTree(leftPreorder, leftInorder);
    root.right = buildTree(rightPreorder, rightInorder);
    return root;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
    System.out.println(Arrays.toString(arr));
    Stack<Integer> stack = new Stack<>();
    ArrayDeque<Integer> q = new ArrayDeque<>();
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);
    q.add(6);
    while (!q.isEmpty()) System.out.println(q.pop());
    // TreeNode root = new PreorderInorder().buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    // System.out.println(root.val);
    // System.out.println(root.left.val);
    // System.out.println(root.right.val);
  }
}
