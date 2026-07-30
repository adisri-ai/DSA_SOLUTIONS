# Question  
```
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 
```
# Constraints  
```
Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
```
# Approach    
1. We maintain an integer `balance` denoting the current balance of parentheses. It must never fall below 0.
2. If the current character of the string is `*` we have three choices: ignore it, consider it as `(` or as `)` we update the balance accordingly.
3. All this is tabulated in a 2d-array using Dynamic programming.
# Code  
```
class Solution {
public:
    bool checkValidString(string s) {
        int n = s.length();
        vector<vector<int>> dp(n + 1, vector<int>(n + 2, 0));
        dp[n][0] = 1;
        for(int i = n - 1; i >= 0; i--) {
            for(int bal = 0; bal <= n; bal++) {
                if(s[i] == '(') {
                    if(bal + 1 <= n)
                        dp[i][bal] = dp[i + 1][bal + 1];
                }
                else if(s[i] == ')') {
                    if(bal > 0)
                        dp[i][bal] = dp[i + 1][bal - 1];
                }
                else {
                    bool ans = dp[i + 1][bal];
                    if(bal + 1 <= n) ans |= dp[i + 1][bal + 1];
                    if(bal > 0) ans |= dp[i + 1][bal - 1];

                    dp[i][bal] = ans;
                }
            }
        }

        return dp[0][0];
    }
};
```
