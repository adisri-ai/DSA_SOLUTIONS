//Question
//Given a set of distinct positive integers nums, 
//return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
//answer[i] % answer[j] == 0, or
//answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int [] dp = new int[n];
        int [] next = new int[n];
        for(int i = 0 ; i<n ; ++i) dp[i] = 1;
        for(int i = 0 ; i<n ; ++i) next[i] = -1;
        int ans = 0;
        for(int i = n-1 ; i>=0 ; --i){
            for(int j = i+1 ; j<n ; ++j){
                if(nums[j]%nums[i] == 0 && 1+dp[j] > dp[i]){
                    next[i] = j;
                    dp[i] = 1+dp[j];
                }
            }
            ans = Math.max(ans , dp[i]);
        }
        int idx = -1;
        for(int i = 0 ; i<n ; ++i){
            if(dp[i]==ans){
                idx = i;
                break;
            }
        }
        List<Integer> l = new ArrayList<>();
        while(idx!=-1){
            l.add(nums[idx]);
            idx = next[idx];
        }
        return l;
    }
}
