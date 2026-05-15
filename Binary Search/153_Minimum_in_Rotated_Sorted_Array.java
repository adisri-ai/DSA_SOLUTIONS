class Solution {
    public int findMin(int[] arr) {
        int n = arr.length;
        int ans = Integer.MAX_VALUE;
        int start = 0;
        int end = n-1;
        int mid;
        while(start<=end){
            mid = start + (end-start)/2;
            ans = Math.min(ans , arr[mid]);
            if(arr[mid]>arr[n-1]) start = mid+1;
            else end = mid-1;
        }
        return ans;
    }
}
