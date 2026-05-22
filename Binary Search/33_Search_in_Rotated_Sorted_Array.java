class Solution {
    public int search(int[] arr, int target) {
        int n = arr.length;
        int start = 0;
        int end = n-1;
        int mid;
        while(start<=end){
            mid = start + (end-start)/2;
            if(arr[mid]>arr[n-1]){
                if(arr[mid]==target) return mid;
                if(arr[mid]<target) start = mid+1;
                else{
                    if(target > arr[n-1]) end = mid-1;
                    else start = mid+1;
                }
            }
            else{
                if(arr[mid]==target) return mid;
                if(arr[mid] > target) end = mid-1;
                else{
                    if(target > arr[n-1]) end = mid-1;
                    else start = mid+1;
                }
            }
        }
        return -1;
    }
}
