// Question
//You are given an integer array nums.
//You want to construct an array result by repeatedly performing the following operation until nums becomes empty:
//Choose an integer k such that 1 <= k <= len(nums).
//Compute the MEX of the first k elements of nums.
//Append this MEX to result.
//Remove the first k elements from nums.
//Return the lexicographically maximum array result that can be obtained after performing the operations.
//The MEX of an array is the smallest non-negative integer not present in the array.
//An array a is lexicographically greater than an array b if in the first position where a and b differ, array a has an element that is greater than the corresponding element in b. If the first min(a.length, b.length) elements do not differ, then the longer array is the lexicographically greater one.

 
class Solution {
public:
    vector<int> maximumMEX(vector<int>& nums) {
        int n = nums.size();
        vector<int>mex(n);
        set<int>st;
        for(int i = 0 ; i<=n+1 ; ++i) st.insert(i);
        for(int i = n-1 ; i>=0 ; --i){
            st.erase(nums[i]);
            mex[i] = *(st.begin());
        }
        int target = mex[0];
        int idx = 0;
        for(int i = 0 ; i<=target ; ++i) st.insert(i);
        vector<int>ans;
        while(idx<n){
            st.erase(nums[idx]);
            if(*(st.begin()) == target){
                ans.push_back(target);
                while(!st.empty()) st.erase(*(st.begin()));
                if(idx==n-1) break;
                idx++;
                target = mex[idx];
                for(int i = 0 ; i<=target ; ++i) st.insert(i);
                continue;
            }
            idx++;
        }
        return ans;
    }
};
