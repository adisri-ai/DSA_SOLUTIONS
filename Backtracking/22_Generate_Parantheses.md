# Question  
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

# Code  
``` 
class Solution {
public:
    vector<string> ans;
    string curr="";
    void trav(int i , int open , int close , int n){
        if(i==2*n){
            ans.push_back(curr); return;
        }
        if(open<n){
            curr+= '(';
            trav(i+1 ,open+1 , close , n );
            curr = curr.substr(0 , curr.length()-1);
        }
        if(close<open){
            curr+= ')';
            trav(i+1 , open , close+1 ,  n);
            curr = curr.substr(0 , curr.length()-1);
        }
    }
    vector<string> generateParenthesis(int n) {
        trav(0 , 0 , 0 , n);
        return ans;
    }
};  
```
