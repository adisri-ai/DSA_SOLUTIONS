# Question  
You are given an integer array `nums`.

You should move each element of `nums` into one of the two arrays `A` and `B` such that `A` and `B` are non-empty, and `average(A) == average(B)`.

Return true if it is possible to achieve that and false otherwise.

Note that for an array `arr`, `average(arr)` is the sum of all the elements of `arr` over the length of `arr`.  
# Constraints  
```
1 <= nums.length <= 30
```
```
0 <= nums[i] <= 1e4
```
# Approach  
1. If two subsets have the same average, their average will be equal to the average of the entire array.
2. For every possible length  of a subset we perform 0/1 Knapsack to check if it possible to create a subset of that length with the same average.

# Code(in C++)  
```
class Solution {
public:

    bool check(int targetSum, int targetSize, vector<int>& nums) {

        vector<vector<bool>> dp(
            targetSize + 1,
            vector<bool>(targetSum + 1, false)
        );

        dp[0][0] = true;

        for (int x : nums) {

            for (int count = targetSize; count >= 1; count--) {

                for (int s = targetSum; s >= x; s--) {

                    dp[count][s] =
                        dp[count][s] ||
                        dp[count - 1][s - x];
                }
            }
        }

        return dp[targetSize][targetSum];
    }


    bool splitArraySameAverage(vector<int>& nums) {

        int n = nums.size();

        int sum = 0;

        for (int x : nums)
            sum += x;

        for (int j = 1; j < n; j++) {
            if ((sum * j) % n != 0)
                continue;

            int targetSum = (sum * j) / n;

            if (check(targetSum, j, nums))
                return true;
        }

        return false;
    }
};
```
