class Solution {
public:
    int jump(vector<int>& nums) {
        int n= nums.size();
        if(n==1) return 0;
        vector<int> dp(n , INT_MAX);
        dp[n-1]=0;
        for(int i=n-2 ; i>=0 ; i--){
            if(nums[i]>=(n-1-i)) dp[i]=1;
            else{
                int m = 1e7;
                int k = nums[i];
                for(int j=1 ; j<=k ; j++){
                    m = min(m , dp[i+j]);
                }
                dp[i]= m+1;
            }           
        }
        return dp[0];
    }
};
