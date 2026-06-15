/*
You are given an integer array nums of length n, and three integers m, l, and r.

Your task is to select at least one and at most m non-overlapping subarrays from nums such that:

Each selected subarray has a length between [l, r] (inclusive).
The total sum of all selected subarrays is maximized.
Return the maximum total sum you can achieve.
*/
class Solution {
public:
    long long maximumSum(vector<int>& nums, int ma, int l, int r) {

        int n = nums.size();

        vector<long long> pref(n + 1, 0);

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        vector<vector<long long>> dp(n + 2, vector<long long>(ma + 1, LLONG_MIN));
        for (int i = 0; i <= n + 1; i++)dp[i][0] = 0;
        for (int m = 1; m <= ma; m++) {
            deque<int> dq;
            for (int i = n; i >= 0; i--) {
                int add = i + l;
                if (add <= n) {
                    long long val =
                        pref[add] + dp[add][m - 1];
                    while (!dq.empty()) {
                        long long backVal = pref[dq.back()] + dp[dq.back()][m - 1];
                        if (backVal > val) break;
                        dq.pop_back();
                    }
                    dq.push_back(add);
                }
                while (!dq.empty() && dq.front() > i + r) {
                    dq.pop_front();
                }

                if (m == 0) {
                    dp[i][m] = 0;
                    continue;
                }

                if (i + l - 1 >= n) {
                    if (m == ma) dp[i][m] = LLONG_MIN;
                    else dp[i][m] = 0;
                    continue;
                }

                long long ntake = dp[i + 1][m];

                long long take = LLONG_MIN;

                if (!dq.empty()) {

                    int k = dq.front();

                    take =
                        (pref[k] - pref[i]) +
                        dp[k][m - 1];
                }

                dp[i][m] = max(take, ntake);
            }
        }

        return dp[0][ma];
    }
};
