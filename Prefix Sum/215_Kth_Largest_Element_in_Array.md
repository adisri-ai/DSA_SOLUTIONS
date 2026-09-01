# Question  
Given an integer array `nums` and an integer `k`, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?  
# Constraints  
`1 <= k <= nums.length <= 1e5`
`-1e4 <= nums[i] <= 1e4`
# Approach    
1. We store a frequency array for numbers ranging from -1e4 to 1e4 with a bias of 1e4.
2. We iterate backwards from the frequency array until we find the kth frequent element.
# Code (in C++)  
```
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        int bias = 1e4;
        vector<int> v(2 * 1e4 + 1, 0); 
        
        for(int num : nums) {
            v[num + bias]++;
        }
        
        int curr = 0;
        for(int i = 2 * 1e4; i >= 0; --i){
            curr += v[i];
            if(curr >= k) {
                return i - bias;
            }
        }
        return -1; 
    }
};

```
