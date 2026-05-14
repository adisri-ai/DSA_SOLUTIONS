// Question
//You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

//Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

//You may assume that you have an infinite number of each kind of coin.

//The answer is guaranteed to fit into a signed 32-bit integer.
class Solution {
public:
    int change(int amount, vector<int>& coins) {
        vector<vector<int>> dp(coins.size() , vector<int>(amount+1 , -1));
        for(int i=0 ; i<dp.size() ; i++){
            dp[i][0]=1;
        }
        for(int j=1 ; j<dp[0].size() ; j++){
            if(j%coins[0]==0){
                dp[0][j]=1;
            }
            else{
                dp[0][j]=0;
            }
        }
        for(int i=1 ; i<dp.size() ; i++){
            for(int j=1; j<dp[0].size() ; j++){
                int take = 0;
                int ntake = dp[i-1][j];
                if(coins[i]<=j) take = dp[i][j-coins[i]];
                dp[i][j]= take+ntake;
            }
        }
        return dp[dp.size()-1][amount];
    }
};
