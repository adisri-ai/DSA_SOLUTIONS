# Question  
You are given a 1-indexed integer array nums.

You can choose two distinct values x and y and perform the following operation at most once:

Replace every occurrence of x in nums with y.

Return the maximum possible number of pairs of adjacent elements that are equal after performing the operation.
# Code  
``` 
class Solution {
public:
    int maxEqualAdjacentPairs(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int , unordered_map<int,int>>mp;
        int add = 0;
        int ans = 0;
        for(int i = 0 ; i<n-1 ; ++i){
            mp[min(nums[i] , nums[i+1])][max(nums[i] , nums[i+1])]++;
            if(nums[i]==nums[i+1]) ans++;
        }
        int curr = ans;
        for(int i =0 ; i<n-1 ; ++i){
            if(nums[i]!=nums[i+1]) {
                curr = max(curr , ans + mp[min(nums[i] , nums[i+1])][max(nums[i] , nums[i+1])]);
                curr = max(curr , ans + mp[min(nums[i] , nums[i+1])][max(nums[i] , nums[i+1])]);
            }
        }
        return curr;
    }
};  
```
