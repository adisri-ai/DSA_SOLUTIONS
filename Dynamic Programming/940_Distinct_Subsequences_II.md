# Question  
Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the 
relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.  

# Code (in C++)  
```
class Solution {
public:
    int distinctSubseqII(string s) {

        const long long M = 1e9 + 7;

        vector<long long> v(26, 0);

        long long dp = 1;   

        for (char ch : s) {

            int c = ch - 'a';

            long long newDp = (2LL * dp - v[c] + M) % M;

            v[c] = dp;

            dp = newDp;
        }

        return (dp - 1 + M) % M;
    }
};
``` 
