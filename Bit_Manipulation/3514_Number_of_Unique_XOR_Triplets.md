# Question  
``` 
You are given an integer array nums.

A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.

Return the number of unique XOR triplet values from all possible triplets (i, j, k).
```
1. We find the maximum number of XOR possible using the bitwise OR of the entire array.
2. Perform state compression using unordered maps to store XOR of pairs before that index.
3. At each index we check for available pairs including the current element to match the target.  
# Code (in C++)  
```
class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int ma = 0;
        int n = nums.size();
        for(int i = 0 ; i<n; ++i) ma|= nums[i];
        unordered_map<int,int>mp;
        vector<int>arr(ma+1 , 0);
        int ans  =0;
        for(int i = 0 ; i<n ; ++i){
            if(!arr[nums[i]]) ans++;
            arr[nums[i]]++;
        }
        for(int i = 0 ; i<n ; ++i){
            for(int j = 0 ; j<i ; ++j) mp[nums[i] ^ nums[j]]++;
            for(int k = 0 ; k<=ma ; ++k){
                if(arr[k]) continue;
                if(mp[k ^ nums[i]]) {ans++; arr[k]++;}
            }
        }
        return ans;
    }
};
```
