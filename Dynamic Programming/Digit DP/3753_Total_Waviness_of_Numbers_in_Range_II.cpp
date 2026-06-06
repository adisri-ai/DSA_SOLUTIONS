// Question 
//You are given two integers num1 and num2 representing an inclusive range [num1, num2].
//The waviness of a number is defined as the total count of its peaks and valleys:
//A digit is a peak if it is strictly greater than both of its immediate neighbors.
//A digit is a valley if it is strictly less than both of its immediate neighbors.
//The first and last digits of a number cannot be peaks or valleys.
//Any number with fewer than 3 digits has a waviness of 0.
//Return the total sum of waviness for all numbers in the range [num1, num2].
class Solution {
public:

    long long dp[20][11][4][2][2];
    long long cntdp[20][2][2];

    long long countNums(int i,
                        int started,
                        int flag,
                        string& s,
                        int n) {
        if (i == n) return 1;
        if (cntdp[i][started][flag] != -1) return cntdp[i][started][flag];
        long long cnt = 0;
        int limit = flag ? s[i] - '0' : 9;
        for (int d = 0; d <= limit; ++d) {
            int nflag = (flag && d == limit);
            cnt += countNums(i + 1, started || d, nflag, s, n);
        }

        return cntdp[i][started][flag] = cnt;
    }

    long long find(int i, int prev, int rel, int started, int flag, string& s, int n) {
        if (i == n) return 0;
        if (dp[i][prev][rel][started][flag] != -1) return dp[i][prev][rel][started][flag];
        long long ans = 0;
        int limit = flag ? s[i] - '0' : 9;
        for (int d = 0; d <= limit; ++d) {
            int nflag = (flag && d == limit);
            if (!started && d == 0) {

                ans += find(i + 1, 0, 3, 0, nflag, s, n);

                continue;
            }

            if (!started) {
                ans += find(i + 1, d, 3, 1, nflag, s, n);

                continue;
            }

            long long add = 0;
            if (rel == 0 && prev > d)add++;
            if (rel == 2 && prev < d) add++;
            int nrel;
            if (prev < d) nrel = 0;
            else if (prev == d) nrel = 1;
            else nrel = 2;

            long long ways = countNums(i + 1, 1, nflag, s, n);

            ans += add * ways;

            ans += find(i + 1,  d, nrel, 1, nflag, s, n);
        }

        return dp[i][prev][rel][started][flag] = ans;
    }

    long long solve(long long x) {

        if (x <= 0) return 0;

        memset(dp, -1, sizeof(dp));
        memset(cntdp, -1, sizeof(cntdp));

        string s = to_string(x);

        return find(0, 0, 3, 0, 1, s, s.length());
    }

    long long totalWaviness(long long num1, long long num2) {

        return solve(num2) - solve(num1 - 1);
    }
};
