# Question 
You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.

The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.

You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can modify any 

of the elements of nums2 by +1 or -1 at most k2 times.

Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.

Note: You are allowed to modify the array elements to become negative integers.   

# Code (Java)  
```  
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];

        for (int i = 0; i < n; ++i) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
        }
        long sum = 0;
        for(int i = 0 ; i<n ; ++i) sum+=diff[i];
        if(k1+k2 >= sum) return 0;
        Arrays.sort(diff);
        long k = (long) k1 + k2;
        int ptr = n - 2;
        long curr = diff[n - 1];

        while (k > 0 && ptr >= 0) {
            long req = (n - 1L - ptr) * (curr - diff[ptr]);
            if (k >= req) {
                curr = diff[ptr];
                k -= req;
                ptr--;
                if(ptr==-1 || k==0) for (int j = n - 1; j > ptr; --j) diff[j] = curr;
            }
            else {
                for (int j = n - 1; j > ptr; --j) diff[j] = curr;
                break;
            }
        }
        if (k > 0) {
            if(diff[n-1]==0) return 0;
            int j = n-1;
            while(j>=0 && diff[j] == diff[n-1]) j--;
            long d = k/(n-1-j);
            long  rem = k%(n-1-j);
            for(int x = n-1 ; x>j ; --x){
                diff[x] = Math.max(0 , diff[x] - d);
                if(rem>0){
                    diff[x] = Math.max(0 , diff[x]-1);
                    rem--;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += diff[i] * diff[i];
        }

        return ans;
    }
}
```
