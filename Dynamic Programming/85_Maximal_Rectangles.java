class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] arr = new int[m][n];
        for(int i = 0 ; i<m ; ++i){
            for(int j = 0 ; j<n ; ++j) arr[i][j] = 0;
        }
        for(int i = 0 ; i<m ; ++i){
            for(int j = 0 ; j<n ; ++j){
                if(matrix[i][j]=='1' && j>0) arr[i][j] = 1+arr[i][j-1];
                if(matrix[i][j] == '1' && j==0) arr[i][j] = 1;
            }
        }
        int [][] dp = new int[m+1][n];
        for(int r = m ; r>=0 ; --r){
            for(int c = 0 ; c<n ; ++c){
                if(r==m) {dp[r][c] = 0; continue;}
                int ans = arr[r][c];
                int curr = 0;
                int ma = arr[r][c];
                for(int i = r+1 ; i<m ; ++i){
                    ans = Math.max(dp[i][c] , ans);
                    ma = Math.min(ma , arr[i][c]);
                    ans = Math.max(ans , ma*(i-r+1));
                }
                dp[r][c] = ans;
            }
        }
        int maxi = dp[0][0];
        for(int j = 1 ; j<n ; ++j) maxi = Math.max(maxi , dp[0][j]);
        return maxi;
    }
}
