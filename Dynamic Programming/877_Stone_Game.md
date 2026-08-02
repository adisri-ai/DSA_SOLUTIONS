# Question  
```
Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row.
This continues until there are no more piles left, at which point the person with the most stones wins.
Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
```
# Constraints  
```
2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles[i]) is odd.
```
# Approach  
1. We use the `Minmax` method implemented using `Dynamic Programming` for this question.
2. We recursively find the best outcome for each player at their turn at finally tabulate it.
3. The state in the `dp` array is defined as `dp[i][j][k]` where `i` is the front pointer, `j` is the end pointer and `k` is the flag.
# Code(in C++)  
```
class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        vector<vector<vector<int>>> dp(n,vector<vector<int>>(n, vector<int>(2, 0)));
        for(int i=0;i<n;i++){
            dp[i][i][0]=piles[i];
            dp[i][i][1]=-piles[i];
        }
        for(int len=2;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j=i+len-1;
                dp[i][j][0]=max(piles[i]+dp[i+1][j][1], piles[j]+dp[i][j-1][1]);
                dp[i][j][1]=min(-piles[i]+dp[i+1][j][0], -piles[j]+dp[i][j-1][0]);
            }
        }
        return dp[0][n-1][0]>0;
    }
};
```
