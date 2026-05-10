class Solution {
    private int findFloor(int x , int start , int end , int[] mi){
        int mid;
        int ans= start-1;
        while(start<=end){
            mid = start + (end-start)/2;
            if(mi[mid]<x){
                ans = mid;
                start = mid+1;
            }
            else end = mid-1;
        }
        return ans;
    }
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int [] ma = new int[n];
        int [] mi = new int[n];
        ma[0] = 0;
        for(int i = 1 ; i<n ; ++i){
            if(nums[i]>nums[ma[i-1]]) ma[i] = i;
            else ma[i] = ma[i-1];
        }
        mi[n-1] = nums[n-1];
        for(int i = n-2 ; i>=0 ; --i) mi[i] = Math.min(mi[i+1] , nums[i]);
        int[] ans = new int[n];
        for(int i = n-1 ; i>=0 ; --i){
            int idx = findFloor(nums[ma[i]] , i+1 , n-1 , mi);
            ans[i] = Math.max(ans[idx] , nums[ma[idx]]);
        }
        return ans;
    }
}
