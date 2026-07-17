# Question  
```
You are given an integer array nums of length n and an integer array queries.

Let gcdPairs denote an array obtained by calculating the GCD of all possible pairs (nums[i], nums[j]), where 0 <= i < j < n, and then sorting these values in ascending order.

For each query queries[i], you need to find the element at index queries[i] in gcdPairs.

Return an integer array answer, where answer[i] is the value at gcdPairs[queries[i]] for each query.

The term gcd(a, b) denotes the greatest common divisor of a and b.
```
# Constraints  
```
Constraints:

2 <= n == nums.length <= 1e5
1 <= nums[i] <= 5 * 1e4
1 <= queries.length <= 1e5
0 <= queries[i] < n * (n - 1) / 2
```
# Approach    
1. We first iterate through the entire array and store the frequency of each element.
2. We iterate from the maximum number `5*1e4` backwards.
3. Upon each iteration we add the total number of multiples of that number present in the array.
4. We substract the the number of multiples of each multiple of the current number in as they cannot have the current number as `gcd`.
5. Finally we take a `prefix sum` to find the position of each gcd in the final array containing `gcd `of each pair.
6. We perform `binary search` on each `query` to get the number at that exact position. This is done by finding the `floor` using binary search.
# Code  
```
class Solution {
public:
    vector<int> gcdValues(vector<int>& nums, vector<long long>& queries) {
        const int MAX = 50000;
        vector<long long> freq(MAX + 1, 0);
        vector<long long> exact(MAX + 1, 0);
        vector<long long> prefix(MAX + 1, 0);
        for (int x : nums)
            freq[x]++;
        for (int g = MAX; g >= 1; g--) {
            long long cnt = 0;
            for (int multiple = g; multiple <= MAX; multiple += g) cnt += freq[multiple];
            exact[g] = cnt * (cnt - 1) / 2;
            for (int multiple = 2 * g; multiple <= MAX; multiple += g)
                exact[g] -= exact[multiple];
        }
        prefix[1] = exact[1];
        for (int i = 2; i <= MAX; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        vector<int> ans;

        for (long long q : queries) {

            int l = 1, r = MAX;

            while (l < r) {

                int mid = (l + r) / 2;

                if (prefix[mid] > q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans.push_back(l);
        }

        return ans;
    }
};
```
