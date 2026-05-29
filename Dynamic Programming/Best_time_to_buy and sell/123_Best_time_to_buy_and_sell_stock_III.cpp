// Question
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//Find the maximum profit you can achieve. You may complete at most two transactions.
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();

        vector<vector<vector<int>>> dp(
            n + 1,
            vector<vector<int>>(2, vector<int>(3, 0))
        );

        for(int i = n - 1; i >= 0; --i) {
            for(int j = 0; j < 2; ++j) {
                for(int k = 1; k <= 2; ++k) {

                    if(j) {
                        int take = prices[i] + dp[i+1][0][k-1];
                        int ntake = dp[i+1][1][k];

                        dp[i][1][k] = max(take, ntake);
                    }
                    else {
                        int take = -prices[i] + dp[i+1][1][k];
                        int ntake = dp[i+1][0][k];

                        dp[i][0][k] = max(take, ntake);
                    }
                }
            }
        }

        return dp[0][0][2];
    }
};
