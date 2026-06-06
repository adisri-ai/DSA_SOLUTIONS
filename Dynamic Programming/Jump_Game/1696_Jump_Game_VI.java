// Question 
//You are given a 0-indexed integer array nums and an integer k.
//You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. 
//That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
//You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
//Return the maximum score you can get.
class Solution {
    public int maxResult(int[] nums, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a,b)->{
        int x = Integer.compare(-a.get(0) , -b.get(0)) ;
        if(x!=0) return x;
        return Integer.compare(-a.get(1) , -b.get(1));});
        int n = nums.length;
        pq.add(List.of(nums[n-1] , n-1));
        int i = n-2;
        int ans = nums[n-1];
        while(i>=0){
            int idx = pq.peek().get(1);
            int val = pq.peek().get(0);
            if(idx > i+k){
                pq.poll();
                continue;
            }
            ans = nums[i] + val;
            pq.add(List.of(ans , i));
            --i;
        }
        return ans;
    }
