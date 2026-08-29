# Question  
You are given a 0-indexed array of positive integers `nums` and a positive integer `limit`.

In one operation, you can choose any two indices `i` and `j` and swap `nums[i]` and `nums[j]` if `|nums[i] - nums[j]| <= limit`.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array `a` is lexicographically smaller than an array `b` if in the first position where `a` and `b` differ, array a has an element that is less than the 
corresponding element in `b`. For example, the array `[2,10,3]` is lexicographically smaller than the array `[10,2,3]` because they differ at
index `0` and `2 < 10`.

# Constraints  
`1 <= nums.length <= 1e5`
`1 <= nums[i] <= 1e9`
`1 <= limit <= 1e9`    

# Approach  
1. We sort the array and find the groups that are connected to each other using the absolute difference `limit`.
2. While iterating through the array we push the elements into the queue corresponding to their group.
3. While pushing the elements to the final array `ans` we extract the `front` element from the queue corresponding to the group the element at that index belongs
   to. 

# Code  
```
class Solution {
public:
    vector<int> lexicographicallySmallestArray(vector<int>& nums, int limit) {
        vector<int>temp = nums;
        int n = nums.size();
        sort(nums.begin() , nums.end());
        unordered_map<int,int>parent;
        unordered_map<int , queue<int>>mp;
        for(int i = 0 ; i<n ; ++i) parent[nums[i]] = nums[i];
        int i = 0;
        while(i<n){
            int j = i+1;
            mp[nums[i]].push(nums[i]);
            while(j<n && abs(nums[j] - nums[j-1])<=limit){
                parent[nums[j]] = nums[i];
                mp[nums[i]].push(nums[j]);
                ++j;
            }
            i = j;
        }
        vector<int>ans;
        for(int i = 0 ; i<n ; ++i){
            int x = mp[parent[temp[i]]].front();
            mp[parent[temp[i]]].pop();
            ans.push_back(x);
        }
        return ans;
    }
};
```
