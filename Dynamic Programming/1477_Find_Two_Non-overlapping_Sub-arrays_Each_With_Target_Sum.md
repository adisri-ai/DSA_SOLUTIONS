# Question  
You are given an array of integers arr and an integer target.

You have to find two non-overlapping sub-arrays of arr each with a sum equal target. There can be multiple answers so you have to find an answer where the 

sum of the lengths of the two sub-arrays is minimum.

Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.  

# Code(C++)    
```
class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {
        int n = arr.size();
        unordered_map<long long , int>mp;
        vector<int>end(n , INT_MAX);
        vector<int>start(n , INT_MAX);
        mp[0] = -1;
        long long curr = 0;
        for(int i = 0 ; i<n ; ++i){
            curr += arr[i];
            if(mp.find(curr - target)!= mp.end()){
                end[i] = i - mp[curr-target];
                start[mp[curr-target] + 1] = i - mp[curr-target];
            }
            mp[curr] = i;
        }
        int start_min = INT_MAX;
        for(int i = n-1 ; i>=0 ;  --i){
            start_min = min(start_min , start[i]);
            start[i] = start_min;
        }
        int end_min = INT_MAX;
        for(int i =0 ; i<n ; ++i){
            end_min = min(end_min , end[i]);
            end[i] = end_min;
        }
        long long ans = LLONG_MAX;
        for(int i = 0 ; i<n-1 ; ++i){
            ans = min(ans , (long long)((long long)end[i] + (long long)start[i+1]));
        }
        if(ans>=INT_MAX) return -1;
        return ans;
    }
};
```
