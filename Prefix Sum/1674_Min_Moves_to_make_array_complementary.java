class Solution {
    public int minMoves(int[] nums, int limit) {
        int start = 0;
        int n = nums.length;
        int end =  nums.length -1;
        int[] pre = new int[2*limit+2];
        int[] suf = new int[2*limit+2];
        HashMap<Integer , Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0 ; i<=2*limit+1 ; ++i){
            pre[i] = 0;
            suf[i] = 0;
        }
        while(start<=end){
            int temp = nums[start]+nums[end];
            pre[Math.max(nums[start] , nums[end]) + limit+1]+=1;
            suf[Math.min(nums[start] , nums[end])]+=1;
            if(!m.containsKey(nums[start]+nums[end])) m.put(nums[start]+nums[end] , 1);
            else m.put(nums[start]+nums[end] , m.get(nums[start]+nums[end])+1);
            start++;
            end--;
        }
        for(int i = 1; i<=2*limit ; ++i) pre[i]+=pre[i-1];
        for(int i = 2*limit-1 ; i>=0; --i) suf[i]+=suf[i+1];
        int ans = (int)1e9+7;
        for(int i = 0 ; i<=2*limit ; ++i){
            int x = 0;
            if(m.containsKey(i)) x = m.get(i);
            ans = Math.min(ans , (pre[i]+suf[i])*2 + (((n/2)+(n%2))-x-pre[i]-suf[i]));
        }
        return ans;
    }
}
