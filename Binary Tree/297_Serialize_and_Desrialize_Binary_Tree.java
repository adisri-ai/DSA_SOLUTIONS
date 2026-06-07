/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Question 
//Serialization is the process of converting a data structure or 
//object into a sequence of bits so that it can be stored in a file or memory buffer,
//or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//Design an algorithm to serialize and deserialize a binary tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) { 
        String ans = "";
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp==null) ans+="|1001|";
            else{
                ans+="|"+(Integer.toString(temp.val))+"|";
                q.add(temp.left);
                q.add(temp.right);
            }
        }
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<Integer> l = new ArrayList<>();
        int i = 0;
        int n = data.length();
        while(i<n-1){
            int j = i+1;
            String temp = "";
            while(data.charAt(j)!='|'){
                temp+=data.charAt(j);
                ++j;
            }
            i = j+1;
            l.add(Integer.parseInt(temp));
        }
        if(l.get(0)==1001) return null;
        TreeNode root = new TreeNode(l.get(0));
        int idx = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(l.get(idx)!=1001){
                temp.left = new TreeNode(l.get(idx));
                q.add(temp.left);
            }
            idx++;
            if(l.get(idx)!=1001){
                temp.right = new TreeNode(l.get(idx));
                q.add(temp.right);
            }
            idx++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
