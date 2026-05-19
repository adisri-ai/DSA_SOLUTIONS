// Question
//Given a string s, return the longest palindromic substring in s.
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
// Explanation
// Here is the recursive approach which was later converted to tabulated form
// 1. We start with two pointers i and j. 
// 2. If s[i] == s[j], we do the following: 
//    a. First choice: Check if the substring between them is palindrome recurisvely or if the the characters are adjacent
//                     or if there is only one character in between then we take this substring length as choice1 else choice1 becomes 0
//    b. Second Chouice: Leave this pair, Either increment i or decrement j and check for the largest palindrome recursively 
