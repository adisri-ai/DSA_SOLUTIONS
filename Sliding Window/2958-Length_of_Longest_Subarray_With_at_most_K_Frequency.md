# Question  
You are given an integer array nums and an integer k.

The frequency of an element x is the number of times it occurs in an array.

An array is called good if the frequency of each element in this array is less than or equal to k.

Return the length of the longest good subarray of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

# Constraints  
`1 <= nums.length <= 1e5`
`1 <= nums[i] <= 1e9`
`1 <= k <= nums.length`  
# Approach  
1. We use sliding window approach to iterate through the entire array.
2. If at any point there exists and element with frequency greater than `k`, we increment the `start` pointer along with decreasing the frequency of the
   element being removed.
3. If the frequency of all elements is less than `k`, we find the subarray size and store the highest size so far in the variable `ans`, we then increment
   the pointer end.
# Code (in Java)  
```
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int start = 0;
        int end = 0;
        HashMap<Integer , Integer> mp= new HashMap<>();
        int ans = 0;
        int curr = 0;
        while(end<n && start<n){
            if(curr>0){
                int x = mp.get(nums[start]);
                if(x>k) curr--;
                mp.put(nums[start] , x-1);
                start++;
            }
            else{
                ans =  Math.max(ans , end-start);
                if(mp.containsKey(nums[end])){
                    int x = mp.get(nums[end]);
                    if(x+1==k+1) curr++;
                    mp.put(nums[end] , x+1);
                    end++;
                }
                else{
                    mp.put(nums[end] , 1);
                    end++;
                }
            }
        }
        if(curr==0) ans = Math.max(ans , end-start);
        return ans;
    }
}
```
