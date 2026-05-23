class Solution {
public:
    void solve(int i,vector<string>& v,int& n,vector<bool>& cols,vector<bool>& dia,vector<bool>& dia2,vector<vector<string>>& ans){
        if(i==n){
            ans.push_back(v);
            return;
        }
        for(int j=0 ; j<n ; j++){
            if(!cols[j] && !dia[j-i+n-1] && !dia2[i+j]){
                v[i][j]='Q';
                cols[j] = 1;
                dia[j-i+n-1] = 1;
                dia2[i+j] = 1;
                solve(i+1 , v , n , cols , dia , dia2 , ans);
                v[i][j] = '.';
                cols[j] = 0;
                dia[j-i+n-1] = 0;
                dia2[i+j] = 0;
            }
        }
    }
    vector<vector<string>> solveNQueens(int n) {
        vector<bool>cols(n , 0);
        vector<bool>dia(2*n-1 , 0);
        vector<bool>dia2(2*n-1 , 0);
        string s = "";
        for(int i=0 ; i<n; i++) s+='.';
        vector<string>v(n ,s);
        vector<vector<string>>ans;
        solve(0 , v, n ,  cols , dia , dia2, ans);
        return ans;
    }
};
