// Question 
//Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

//[4,5,6,7,0,1,2] if it was rotated 4 times.
//[0,1,2,4,5,6,7] if it was rotated 7 times.
//Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//Given the sorted rotated array nums of unique elements, return the minimum element of this array.
//You must write an algorithm that runs in O(log n) time.
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
