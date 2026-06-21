# Question   
```
    Construct a Binary Tree given the sequence of it's preorder and inorder traversal. Return the root of the constructed tree.
```
# Approach  
1. We maintain a static index to traverse across the preorder sequentially.
2. Every node to the left of the location of the current preorder node in the inorder array will be to it's left while others would be to the right.
3. We recursively call the function to get the root of the left and right subtrees.
# Code (in Java)
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int  idx = 0;
    public TreeNode find(int start , int end , int[] preorder , int[]  inorder, HashMap<Integer, Integer> mp){
        if(start>end) return null;
        int j = mp.get(preorder[idx]);
        TreeNode root = new TreeNode(inorder[j]);
        idx++;
        root.left = find(start , j-1 , preorder , inorder,mp);
        root.right = find(j+1 , end , preorder , inorder , mp);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        HashMap<Integer , Integer>mp = new HashMap<>();
        for(int i = 0 ; i<n ; ++i) mp.put(inorder[i] , i);
        return find(0 , preorder.length-1 , preorder , inorder , mp);
    }
}
