/*
 * *** YOUR NAME GOES HERE / YOUR SECTION NUMBER ***
 *
 * This java file is a Java object implementing a simple AVL Tree.
 * Complete the deleteElement method as shown.
 *
 */

 import java.lang.Math;

 /**
  *  Class: Node
  *
  *  This class represents an inner node of the AVL Tree.
  *  Each node maintains:
  *    - value : the node's data
  *    - height: the height of the node (max edges to a leaf)
  *    - leftChild, rightChild: pointers to left and right subtrees
  */
 class Node {
     int value;
     int height;
     Node leftChild, rightChild;
 
     public Node(int data) {
         value = data;
         height = 0;
         leftChild = rightChild = null;
     }
 }
 
 /**
  *  Class 'LUC_AVLTree'
  *
  *  Provides methods to insert, delete, and traverse an AVL tree.
  */
 class LUC_AVLTree {
     private Node rootNode;
 
     public LUC_AVLTree()              { rootNode = null; }
     public void removeAll()           { rootNode = null; }
     public boolean checkEmpty()       { return rootNode == null; }
     public void insert(int value)     { rootNode = insertElement(value, rootNode); }
     public void delete(int value)     { rootNode = deleteElement(value, rootNode); }
     public String preorderTraversal() { return preorderTraversal(rootNode); }
 
     private int getHeight(Node node)  { return node == null ? -1 : node.height; }
     private int getMaxHeight(int h1, int h2) { return h1 > h2 ? h1 : h2; }
 
     // -------------- Utility Methods ----------------
 
     private int getBalanceFactor(Node node) {
         if (node == null) return 0;
         int leftH  = getHeight(node.leftChild)  + 1;
         int rightH = getHeight(node.rightChild) + 1;
         return leftH - rightH;
     }
 
     private Node minValueNode(Node node) {
         Node current = node;
         while (current.leftChild != null)
             current = current.leftChild;
         return current;
     }
 
     private String preorderTraversal(Node node) {
         if (node == null)
             return "";
         return node.value + " " + preorderTraversal(node.leftChild) + preorderTraversal(node.rightChild);
     }
 
     // -------------- Insert Method (already implemented) ----------------
 
     private Node insertElement(int value, Node node) {
         if (node == null) {
             node = new Node(value);
             return node;
         }
 
         if (value < node.value) {
             node.leftChild = insertElement(value, node.leftChild);
             int bf = getBalanceFactor(node);
             if (Math.abs(bf) > 1) {
                 if (value < node.leftChild.value)
                     node = LLRotation(node);
                 else
                     node = LRRotation(node);
             }
         } else if (value > node.value) {
             node.rightChild = insertElement(value, node.rightChild);
             int bf = getBalanceFactor(node);
             if (Math.abs(bf) > 1) {
                 if (value > node.rightChild.value)
                     node = RRRotation(node);
                 else
                     node = RLRotation(node);
             }
         } // Duplicate values are ignored.
 
         node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
         return node;
     }
 
     // -------------- deleteElement Method ----------------
     private Node deleteElement(int value, Node node) {
         // YOUR CODE GOES HERE
         // -----------------------------------------------------
         // 1. Base Case: If node is null, return null.
         // 2. Traverse to locate the node to delete:
         //    a. If value is less than node.value, recursively delete from left.
         //    b. If value is greater than node.value, recursively delete from right.
         // 3. Node Found: Handle deletion cases:
         //    a. If the node has zero or one child, replace it with its non-null child (or null).
         //    b. If the node has two children, find the inorder successor (min node in right subtree),
         //       copy its value, and delete the inorder successor.
         // 4. Update the node's height.
         // 5. Check the balance factor and perform rotations (LL, LR, RR, RL) if needed.
         // -----------------------------------------------------
         
         // Step 1: Base Case
         if (node == null)
             return null;
         
         // Step 2: Traverse to locate the node
         if (value < node.value) {
             node.leftChild = deleteElement(value, node.leftChild);
         } else if (value > node.value) {
             node.rightChild = deleteElement(value, node.rightChild);
         } else {
             // Step 3: Node found; handle deletion.
             if (node.leftChild == null || node.rightChild == null) {
                 // 3a: Node with zero or one child.
                 Node temp = (node.leftChild != null) ? node.leftChild : node.rightChild;
                 node = temp;
             } else {
                 // 3b: Node with two children.
                 Node temp = minValueNode(node.rightChild);
                 node.value = temp.value;
                 node.rightChild = deleteElement(temp.value, node.rightChild);
             }
         }
         
         // If the node became null (i.e., leaf deleted), return.
         if (node == null)
             return node;
         
         // Step 4: Update height
         node.height = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
         
         // Step 5: Rebalance the node if necessary.
         int balance = getBalanceFactor(node);
         
         // Left Left Case
         if (balance > 1 && getBalanceFactor(node.leftChild) >= 0)
             return LLRotation(node);
         
         // Left Right Case
         if (balance > 1 && getBalanceFactor(node.leftChild) < 0)
             return LRRotation(node);
         
         // Right Right Case
         if (balance < -1 && getBalanceFactor(node.rightChild) <= 0)
             return RRRotation(node);
         
         // Right Left Case
         if (balance < -1 && getBalanceFactor(node.rightChild) > 0)
             return RLRotation(node);
         
         return node;
     }
 
     // -------------- Rotation Methods ----------------
 
     private Node LLRotation(Node x) {
         Node y = x.leftChild;
         x.leftChild = y.rightChild;
         y.rightChild = x;
         x.height = getMaxHeight(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
         y.height = getMaxHeight(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
         return y;
     }
 
     private Node LRRotation(Node x) {
         Node y = x.leftChild;
         Node z = x.leftChild.rightChild;
         y.rightChild = z.leftChild;
         x.leftChild  = z.rightChild;
         z.leftChild  = y;
         z.rightChild = x;
         x.height = getMaxHeight(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
         y.height = getMaxHeight(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
         z.height = getMaxHeight(getHeight(z.leftChild), getHeight(z.rightChild)) + 1;
         return z;
     }
 
     private Node RRRotation(Node x) {
         Node y = x.rightChild;
         x.rightChild = y.leftChild;
         y.leftChild = x;
         x.height = getMaxHeight(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
         y.height = getMaxHeight(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
         return y;
     }
 
     private Node RLRotation(Node x) {
         Node y = x.rightChild;
         Node z = x.rightChild.leftChild;
         y.leftChild  = z.rightChild;
         x.rightChild = z.leftChild;
         z.rightChild = y;
         z.leftChild  = x;
         x.height = getMaxHeight(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
         y.height = getMaxHeight(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
         z.height = getMaxHeight(getHeight(z.leftChild), getHeight(z.rightChild)) + 1;
         return z;
     }
 }
 