// Question
//Given an array of integers arr and an integer d. In one step you can jump from index i to index:
//i + x where: i + x < arr.length and  0 < x <= d.
//i - x where: i - x >= 0 and  0 < x <= d.
//In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for 
//all indices k between i and j (More formally min(i, j) < k < max(i, j)).
//You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
//Notice that you can not jump outside of the array at any time.
class Solution {
    private int find(int i , int[] arr , int[] dp , int d){
        if(i==dp.length) return 0;
        if( dp[i]!=-1) return dp[i];
        int take  = 1;
        for(int k = i-1 ; k>=Math.max(0 , i-d) ; --k){
            if(arr[k]<arr[i]) take = Math.max(take , 1+find(k , arr , dp , d));
            else break;
        }
        for(int k = i+1 ; k<=Math.min(i+d,arr.length-1) ;++k){
            if(arr[k]<arr[i]) take = Math.max(take , 1+find(k , arr , dp , d));
            else break;
        }
        return dp[i] = take;
    }
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        for(int i = 0 ; i<n ; ++i) dp[i] = -1;
        for(int i = 0 ; i<n ; ++i){
            System.out.println(i);
            if(dp[i]==-1) find(i, arr , dp , d);
        }
        int ans = 0;
        for(int i = 0 ; i<n ; ++i) ans = Math.max(ans , dp[i]);
        return ans;
    }
}
// Explanation
// 1. Since there is no specific order in which cells couild be accessed, we make use of memoizaiton rather than tabulation. 
// 2. For every i iterate acorss neightbouring cells as long as the sequence is stricly decrasing 
// 3. We recursively apply the "find" function functions and finally take the maximum value. 
