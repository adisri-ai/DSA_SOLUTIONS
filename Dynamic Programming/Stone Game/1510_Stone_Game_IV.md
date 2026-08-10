# Question  
```
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
```
# Constraints  
```
1<=n<=1e5
```
# Approach  
1. We use a bottom-up DP approach for the given problem.
2. We take the base case: If the value of `n` becomes 0 the player with the current turn looses.
3. Based on this base case we keep on adding perfect squares such that the sum is `<=n` and stick to the same final output.
4. For `Alice` we use the `||` operator because she shall try choosing the path that can win her the game.
5. For `Bob` we use the `&&` operator because he shall try choosing the path by which Alice losses and he wins the game.
# Code (in C++)  
```
class Solution {
public:
    bool winnerSquareGame(int n) {
        int temp = sqrt(n);
        vector<vector<int>>dp(n+1 , vector<int>(2 , -1));
        dp[0][0] = 0;
        dp[0][1] = 1;
        for(int i = 0 ; i<n ; ++i){
            for(int j = 1 ; j<=temp ; ++j){
                int x = i + (j*j);
                if(x<=n){
                    dp[x][1] = dp[x][1]==-1 ? dp[i][0] : dp[x][1] && dp[i][0]; 
                    dp[x][0] = dp[x][0]==-1 ? dp[i][1] : dp[x][0] || dp[i][1];
                }
            }
        }
        return dp[n][0];
    }
};
```
