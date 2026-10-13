# Question  
You are given an integer array nums. You may rearrange the elements in any order.

The alternating score of an array arr is defined as:

score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.  

# Code (Python)  
``` 
class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        nums = sorted([num**2 for num in nums])
        n = math.floor(len(nums)/2)
        return sum(nums[n:])-sum(nums[:n])

```
