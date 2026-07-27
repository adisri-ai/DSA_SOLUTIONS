# Question  
```
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
such that the absolute difference between any two elements of this subarray is less than or equal to limit.
```
# Constraints  
```
1 <= nums.length <= 1e5
1 <= nums[i] <= 1e9
0 <= limit <= 1e9
```
# Approach  
1. We iterate through every index `i` in the array `nums` and perform binary search to find the farthest point till where the subarray is valid.
2. To validate the subarray we make use of segmentree queries on `maxtree` and `mintree` to find the max and min elements of the subarray and check the absolute
   difference.
# Code(in C++)   
```
class Solution {
public:

    vector<int> maxTree, minTree;

    void buildMax(int node, int l, int r, vector<int>& nums) {

        if (l == r) {
            maxTree[node] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        buildMax(2 * node + 1, l, mid, nums);
        buildMax(2 * node + 2, mid + 1, r, nums);

        maxTree[node] = max(maxTree[2 * node + 1],
                            maxTree[2 * node + 2]);
    }

    void buildMin(int node, int l, int r, vector<int>& nums) {

        if (l == r) {
            minTree[node] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        buildMin(2 * node + 1, l, mid, nums);
        buildMin(2 * node + 2, mid + 1, r, nums);

        minTree[node] = min(minTree[2 * node + 1],
                            minTree[2 * node + 2]);
    }

    int queryMax(int node,
                 int l,
                 int r,
                 int ql,
                 int qr) {

        if (r < ql || l > qr)
            return INT_MIN;

        if (ql <= l && r <= qr)
            return maxTree[node];

        int mid = (l + r) / 2;

        return max(queryMax(2 * node + 1, l, mid, ql, qr),
                   queryMax(2 * node + 2, mid + 1, r, ql, qr));
    }

    int queryMin(int node,
                 int l,
                 int r,
                 int ql,
                 int qr) {

        if (r < ql || l > qr)
            return INT_MAX;

        if (ql <= l && r <= qr)
            return minTree[node];

        int mid = (l + r) / 2;

        return min(queryMin(2 * node + 1, l, mid, ql, qr),
                   queryMin(2 * node + 2, mid + 1, r, ql, qr));
    }

    int longestSubarray(vector<int>& nums, int limit) {

        int n = nums.size();

        maxTree.assign(4 * n, 0);
        minTree.assign(4 * n, 0);

        buildMax(0, 0, n - 1, nums);
        buildMin(0, 0, n - 1, nums);

        int ans = 1;

        for (int left = 0; left < n; left++) {
            int lo = left;
            int hi = n - 1;
            int best = left;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                int mx = queryMax(0,0,n - 1,left,mid);
                int mn = queryMin(0,0,n - 1,left,mid);
                if (mx - mn <= limit) {
                    best = mid;
                    lo = mid + 1;
                }
                else {
                    hi = mid - 1;
                }
            }

            ans = max(ans, best - left + 1);
        }
```

        return ans;
    }
};
