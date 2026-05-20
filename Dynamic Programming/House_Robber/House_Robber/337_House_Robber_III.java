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
    private int find(TreeNode root , HashMap<TreeNode , Integer> dp){
        if(root==null) return 0;
        if(dp.containsKey(root)) return dp.get(root);
        int ans= 0;
        int take = root.val;
        if(root.left!=null){
            take+=find(root.left.left , dp);
            take+=find(root.left.right, dp);
        }
        if(root.right!=null){
            take+=find(root.right.left , dp);
            take+=find(root.right.right , dp);
        }
        int ntake = 0;
        ntake+=find(root.left , dp);
        ntake+=find(root.right , dp);
        dp.put(root , Math.max(take , ntake));
        return dp.get(root);
    }
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> dp = new HashMap<>();
        return find(root , dp);
    }
}
