//Question
//Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
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
// Approach : 
// 1. For every row in the matrix we record its current no of consectuive 1's from it's left and store it in the array arr. 
// 2. We perform dp on every column seperately. For every cell in that column we have two choices: 
//      i) Start a new rectangle 
//      ii) Continue with the rectangle from beginning for this we assume width as the min prefix among all the cells traversed in the column so far.
// 3. Finally we take the max value of final dp (0th row) among all the columns 
