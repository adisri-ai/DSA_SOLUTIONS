# Questions  
You are given an integer array nums containing distinct positive integers and an integer target.

Determine if you can partition nums into two non-empty disjoint subsets, with each element belonging to exactly one subset, such that the product of the 

elements in each subset is equal to target.

Return true if such a partition exists and false otherwise.

A subset of an array is a selection of elements of the array.   

# Code (in C++)  
``` 
class Solution {
public:
    bool find(int i , vector<int>& nums , long long target){
        if(target==1) return true;
        if(i==nums.size()) return false;
        if(nums[i] > target) return false;
        if(target%nums[i] == 0){
            if(find(i+1 , nums , target/nums[i])) return true;
        }
        return find(i+1 , nums , target);
    }
    bool checkEqualPartitions(vector<int>& nums, long long target) {
        double prod = 1.00;
        double sq = (double)(target)*(double)(target);
        for(auto& num : nums){
            prod*=num;
            if(prod>sq) return false;
        }
        if(prod!=sq) return false;
        sort(nums.begin() , nums.end());
        if(target==1) return *min_element(nums.begin() , nums.end()) == 1;
        return find(0 , nums , target);
    }
};  
```
