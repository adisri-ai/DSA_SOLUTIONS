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
