# Question  
You are given a binary array `nums`.

We call a subarray **alternating** if **no** two **adjacent** elements in the subarray have the **same value**.

Return the number of *alternating subarrays* in `nums`.  

# Code(in C++)  
```
class Solution {
public:
    long long countAlternatingSubarrays(vector<int>& nums) {
        int n = nums.size();
        long long curr = 0;
        long long ans = 0;
        for(int i = 0 ; i<n ; ++i){
            if(!i || nums[i]!=nums[i-1]){
                curr++;
            }
            else curr = 1;
            ans+=curr;
        }
        return ans;
    }
};
```
