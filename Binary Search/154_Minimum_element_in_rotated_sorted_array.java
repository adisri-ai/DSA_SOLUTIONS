class Solution {
public:
    int findMin(vector<int>& nums) {
        int n = nums.size();
        int start = 0;
        int end = n-1;
        int ans = nums[0];
        int mid;
        while(start<=end){
            mid = start + (end-start)/2;
            ans = min(ans , nums[mid]);
            if(nums[mid]>nums[end]) start = mid+1;
            else if(nums[mid]==nums.back()) end--;
            else{
                ans = min(ans , nums[mid]);
                end = mid-1;
            }
        }
        return ans;
    }
};
