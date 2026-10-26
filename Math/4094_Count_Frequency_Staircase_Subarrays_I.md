# Question  
You are given an integer array nums.

Consider the distinct values of a subarray in the order in which they first appear.

If there are k distinct values, the subarray is called a frequency staircase if the first distinct value occurs exactly once, the second distinct value occurs exactly twice, and so on, with the kth distinct value occurring exactly k times.

Equivalently, the frequencies of the distinct values, in the order of their first occurrence, must be exactly 1, 2, 3, ..., k.

Return the number of frequency staircase subarrays in nums.

A subarray is a contiguous non-empty sequence of elements within an array.

The frequency of a value in a subarray is the number of times it occurs in that subarray.

# Code (in C++)  
```  
class Solution {
public:
    int countStaircase(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        vector<int>v(n+1 , 1);
        for(int i = 0 ; i<n ; ++i){
            for(auto& k : v) k = 1;
            int curr = 1;
            unordered_map<int,int>pos;
            unordered_map<int,int>mp;
            int ptr = 1;
            for(int j = i ; j<n ; ++j){
                if(!mp[nums[j]]){
                    pos[nums[j]] = curr++;
                }
                mp[nums[j]]++;
                if(mp[nums[j]] > pos[nums[j]]) break;
                if(mp[nums[j]] == pos[nums[j]]) v[pos[nums[j]]] = 0;
                while(ptr<=n && !v[ptr]) ptr++;
                int val = ptr - 1;
                if((val*(val+1))/2 == j-i+1) ans++;
            }
        }
        return ans;
    }
};
```
