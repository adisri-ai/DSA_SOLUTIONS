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
