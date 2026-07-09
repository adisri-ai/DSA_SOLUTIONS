# Question  
```
You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where queries[i] = [li, ri].

For each queries[i], extract the substring s[li..ri]. Then, perform the following:

Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x. The answer is x * sum.
Return an array of integers answer where answer[i] is the answer to the ith query.

Since the answers may be very large, return them modulo 109 + 7.
```
# Constraints 
```

1 <= m == s.length <= 1e5
s consists of digits only.
1 <= queries.length <= 1e5
queries[i] = [li, ri]
0 <= li <= ri < m
```
# Approach  
1. We maintain prefix arrays for sum of digits, conactenated number and length of the concatenated number.
2. To answer each query we simply subtract the prefix sums to produce the sum and obtain conactenated by subtracting from the earlier prefix after multiplying by
   power of 10 according to the difference in length.
# Code(in C++)  
```
class Solution {
public:
    vector<int> sumAndMultiply(string s, vector<vector<int>>& queries) {
        int n = s.length();
        long long M = 1e9 + 7;

        vector<long long> pre(n);
        vector<long long> num(n);
        vector<long long> count(n);
        vector<long long> pow10(n + 1);
        pow10[0] = 1;
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % M;

        long long sum = 0;
        long long curr = 0;
        int cnt = 0;

        for (int i = 0; i < n; ++i) {
            sum += (s[i] - '0');

            if (s[i] != '0') {
                curr = (curr * 10 + (s[i] - '0')) % M;
                cnt++;
            }

            pre[i] = sum;
            num[i] = curr;
            count[i] = cnt;
        }

        vector<int> ans;

        for (auto &q : queries) {

            int l = q[0];
            int r = q[1];

            long long x = num[r];
            long long diff = count[r] - (l ? count[l - 1] : 0);
            long long y = (l ? num[l - 1] : 0);
            y = (y * pow10[diff]) % M;
            long long temp = (x - y + M) % M;
            long long digitSum = pre[r] - (l ? pre[l - 1] : 0);

            ans.push_back((temp * digitSum) % M);
        }

        return ans;
    }
};
```
