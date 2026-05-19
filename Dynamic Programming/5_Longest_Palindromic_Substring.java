
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int [][] dp = new int[n][n];
        for(int start = n-1 ; start>=0 ; --start){
            for(int end = 0 ; end<n ; ++end){
                if(start>end) {dp[start][end]= 0; continue;}
                if(start==end) {dp[start][end] =1; continue;}
                int ans = 0;
                if(s.charAt(start)==s.charAt(end)){
                    if(start==end-1) {dp[start][end]=2; continue;};
                    int x = dp[start+1][end-1];
                    if(x==end-start-1) ans = Math.max(ans , 2+x);
                }
                ans = Math.max(ans , Math.max(dp[start+1][end] , dp[start][end-1]));
                dp[start][end] = ans;
            }
        }
        int k = dp[0][n-1];
        int f = -1;
        int se = -1;
        int curr = Integer.MAX_VALUE;
        for(int i = 0 ; i<n ; ++i){
            for(int j = 0 ; j<n ; ++j){
                if(dp[i][j]==k && j-i+1 < curr){
                    curr = j-i+1;
                    f = i;
                    se = j;
                }
            }
        }
        return s.substring(f , se+1);
    }
}
