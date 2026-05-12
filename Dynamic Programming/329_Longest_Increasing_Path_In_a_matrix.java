class Solution {
    private int[] dr = {-1 , 0 , 1 , 0};
    private int[] dc = {0 , 1 , 0 , -1};
    private int find(int i , int j , int [][] dp , int [][] matrix , int m , int n){
        if(dp[i][j]!=-1) return dp[i][j];
        dp[i][j] = 0;
        int ans = 1;
        for(int k = 0 ; k<4 ; ++k){
            int nr = i+dr[k];
            int nc = j+dc[k];
            if(nr>=0 && nr<m && nc>=0 && nc<n && matrix[nr][nc]>matrix[i][j] && dp[nr][nc]!=0){
                ans=  Math.max(ans , 1 + find(nr , nc , dp , matrix , m , n));
            }
        }
        return dp[i][j] = ans;
    }
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] dp = new int[m][n];
        for(int i = 0 ; i<m ; ++i){
            for(int j = 0 ; j<n ; ++j){
                dp[i][j]=-1;
            }
        }
        int ans = 0;
        for(int i = 0 ; i<m ; ++i){
            for(int j = 0 ; j<n ; ++j){
                if(dp[i][j]==-1) find(i , j , dp , matrix , m , n);
                ans = Math.max(ans , dp[i][j]);
            }
        }
        return ans;
    }
}
