# Question   
You are given a string s and a positive integer k.

Select a set of non-overlapping substrings from the string s that satisfy the following conditions:

The length of each substring is at least k.
Each substring is a palindrome.
Return the maximum number of substrings in an optimal selection.

A substring is a contiguous sequence of characters within a string.   

# Code  
```
class Solution {
public:
    int maxPalindromes(string s, int k) {

        int n = s.size();
        vector<int> dp(n, 0);

        for (int center = 0; center < n; ++center) {

            if (center > 0)
                dp[center] = max(dp[center], dp[center - 1]);

            int left = center;
            int right = center;

            while (left >= 0 && right < n &&
                   s[left] == s[right]) {

                if (right - left + 1 >= k) {

                    int before = (left == 0) ? 0 : dp[left - 1];

                    dp[right] = max(dp[right], before + 1);
                }

                left--;
                right++;
            }

            left = center;
            right = center + 1;

            while (left >= 0 && right < n &&
                   s[left] == s[right]) {

                if (right - left + 1 >= k) {

                    int before = (left == 0) ? 0 : dp[left - 1];

                    dp[right] = max(dp[right], before + 1);
                }

                left--;
                right++;
            }
        }

        for (int i = 1; i < n; ++i)
            dp[i] = max(dp[i], dp[i - 1]);

        return dp[n - 1];
    }
};
```
