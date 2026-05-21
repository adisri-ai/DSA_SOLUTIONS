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
