# Question  
You are given an integer array nums of length n.

A pair of indices (i, j) is called a shadow pair if all of the following conditions are satisfied:

`0 <= i < j < n`
`nums[i] < nums[j]`
There does not exist an index k such that `i < k < j` and `nums[k] < nums[i] < nums[j].`
Return the total number of shadow pairs.     

# Code (in C++)   
```
class Solution {
public:
    long long shadowPairs(vector<int>& nums) {
        int n = nums.size();
        stack<int>st;
        vector<int>next(n , n);
        for(int i = n-1 ; i>=0 ; --i){
            while(!st.empty() && nums[i] <= nums[st.top()]){
                st.pop();
            }
            if(!st.empty()) next[i] = st.top();
            st.push(i);
        }
        unordered_map<int,queue<int>>mp;
        long long ans= 0;
        for(int i = n-1 ; i>=0 ; --i){
            int start_idx = i+1;
            int end_idx = next[i]-1;
            while(!mp[nums[i]].empty() && mp[nums[i]].front() > end_idx) mp[nums[i]].pop();
            int total = max(0 , end_idx - start_idx+1) - mp[nums[i]].size();
            mp[nums[i]].push(i);
            ans+=total;
        }
        return ans;
    }
};
```
