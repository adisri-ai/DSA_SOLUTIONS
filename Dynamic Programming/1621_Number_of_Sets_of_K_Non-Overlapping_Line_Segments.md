# Question  
Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i, find the number of ways we can draw exactly k non-overlapping line segments 
such that each segment covers two or more points. The endpoints of each segment must have integral coordinates. The k line segments do not have to cover 
all n points, and they are allowed to share endpoints.
Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it modulo 109 + 7.

# Code(in C++) 
``` 
class Solution {

public:

    long long M = 1e9 + 7;

    int numberOfSets(int n, int k) {

        vector<vector<long long>> dp(k + 1,
                                     vector<long long>(2, 0));

        dp[0][0] = 1;

        for(int i = 0; i < n; ++i) {

            vector<vector<long long>> ndp(
                k + 1,
                vector<long long>(2, 0)
            );

            for(int j = 0; j <= k; ++j) {
                if(dp[j][0]) {
                    ndp[j][0] =
                        (ndp[j][0] + dp[j][0]) % M;

                    ndp[j][1] =
                        (ndp[j][1] + dp[j][0]) % M;
                }

                if(dp[j][1]) {

                    ndp[j][1] =
                        (ndp[j][1] + dp[j][1]) % M;

                    if(j < k) {
                        ndp[j + 1][0] =
                            (ndp[j + 1][0] + dp[j][1]) % M;
                    }

                    if(j < k) {
                        ndp[j + 1][1] =
                            (ndp[j + 1][1] + dp[j][1]) % M;
                    }
                }
            }

            dp = ndp;
        }

        return dp[k][0];
    }
};  
```

 
