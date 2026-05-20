// Question 
//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//Given an integer array nums representing the amount of money of each house, 
//return the maximum amount of money you can rob tonight without alerting the police.
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1]=  Math.max(nums[1] , nums[0]);
        for(int i = 2 ; i<n  ; ++i){
            dp[i] = Math.max(dp[i-2]+nums[i] , dp[i-1]);
        }
        return dp[n-1];
    }
}
//Explanation
//At every house we have two choices : 
// 1. Rob the huse and add it to dp[i-2]
// 2. Don't rob the house and continue with dp[i-1]
// We take the maximum of the two choices for every house.
