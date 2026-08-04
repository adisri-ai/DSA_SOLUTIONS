# Question  
```
You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0.
At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1.
The player adds the chosen number to their score. The game ends when there are no more elements in the array.
Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true.
You may assume that both players are playing optimally.
```
# Constraints  
```  
1 <= nums.length <= 20
0 <= nums[i] <= 1e7
```
# Approach  
1. We use the `Min-max` algorithm-like approach here and tabulate it using `Dynamic Programming`.
2. One player tries to maximize the final score and the other tries to minimize it.
3. We choose the best possible move for each player at every stage.
4. Finally we check the final value to predict the winner.
# Code (in C++)  
```
class Solution {
public:
    bool predictTheWinner(vector<int>& piles) {
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
        return dp[0][n-1][0]>=0;
    }
};
``` 
