# Question  
```
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
Return a boolean indicating whether the matching covers the entire input string (not partial).
```
# Constraints  
```
1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
```
# Approach  
1. If the current pointed characters of `s` and `p` don't match and the next character in `p` is not `*`, we return  `false` for that subproblem.
2. If the next character in p is `*` we have two choices for the next problem subproblem: keeping the pointer for `p` in the same position or incrementing it.
3. If at only substring left from pointer to the end for `p` is `.*` we return `true`.
4. If we have reached the end of both the pointers (for `s` and `p`) at the same time we return `true`.
# Code  
```
class Solution {
public:
    bool isMatch(string s, string p) {
        int n1 = s.length();
        int n2 = p.length();
        vector<vector<int>>dp(n1+1 , vector<int>(n2+1 , false));
        for(int i = n1 ; i>=0 ; --i){
            for(int j = n2 ; j>=0 ; --j){
                if(j==n2-2 && p[j]=='.' && p[j+1]=='*'){
                    dp[i][j] = true;
                    continue;
                }
                if(j+1<n2 && p[j+1]=='*') dp[i][j] = dp[i][j] || dp[i][j+2];
                if(dp[i][j]==true) continue;
                if(i==n1) {dp[i][j] = (j==n2); continue;}
                if(p[j]!='.' && p[j]!='*'){
                    if(s[i]!=p[j]){
                        if(j+1<n2 && p[j+1]=='*') {dp[i][j]= dp[i][j+2]; continue;}
                        dp[i][j] =false;
                        continue;
                    }
                    if(s[i]==p[j] && j+1<n2 && p[j+1]=='*'){dp[i][j] = dp[i+1][j] || dp[i+1][j+2] ; continue;}
                    dp[i][j] = dp[i+1][j+1];
                    continue;
                }
                if(j+1<n2 && p[j+1]=='*') {dp[i][j] = dp[i+1][j] || dp[i+1][j+2] ; continue;}
                dp[i][j] = dp[i+1][j+1];
            }
        }
        return dp[0][0];
    }
};
```
