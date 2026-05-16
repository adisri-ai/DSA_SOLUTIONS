/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int ans= -1000;
    int path(TreeNode* root){
        if(root==NULL){
            return 0;
        }
        int v = root->val;
        int left = path(root->left);
        int right = path(root->right);
        if(left<0){
            left=0;
        }
        if(right<0){
            right=0;
        }
        ans = max(ans , v+left+right);
        return v+ max(left , right);
    }
    int maxPathSum(TreeNode* root) {
        path(root);
        return ans;
    }
};
