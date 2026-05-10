// Question
// You are given a 0-indexed array nums of n integers and an integer target.
//You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:
//0 <= i < j < n
//-target <= nums[j] - nums[i] <= target
// Return the maximum number of jumps you can make to reach index n - 1.
// If there is no way to reach index n - 1, return -1.
class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for(int i = n-2 ; i>=0 ; --i){
            int ans =-1;
            for(int j = i+1 ; j<n ; ++j){
                if(dp[j]!=-1 && Math.abs(nums[j]-nums[i])<=target) ans = Math.max(ans , 1+dp[j]);
            }
            dp[i] = ans;
        }
        return dp[0];
    }
}
// Explanation: 
// We first define a dp array where dp[i] stores the maximum number of jumps we can take to reach n-1 index. 
// We iterate through the array backwards as overlapping subproblems occur for future indices which are now tabulated by dp array 
// According to the constraints the jump can be performed only when difference between values at indices is less than target
