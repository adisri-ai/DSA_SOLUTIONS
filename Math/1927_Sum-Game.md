# Question  
Alice and Bob take turns playing a game, with Alice starting first.

You are given a string num of even length consisting of digits and '?' characters. On each turn, a player will do the following if there is still at least one '?'
in num:
Choose an index `i` where `num[i] == '?'`.
Replace `num[i]` with any digit between '0' and '9'.
The game ends when there are no more '?' characters in num.

For Bob to win, the sum of the digits in the first half of num must be equal to the sum of the digits in the second half. 
For Alice to win, the sums must not be equal.

For example, if the game ended with num = "243801", then Bob wins because 2+4+3 = 8+0+1. If the game ended with num = "243803", 
then Alice wins because 2+4+3 != 8+0+3. Assuming Alice and Bob play optimally, return true if Alice will win and false if Bob will win.  

# Constraints  
`2 <= num.length <= 1e5`
`num.length is even`.
`num consists of only digits and '?'`.  
# Code (in C++)  
```
class Solution {
public:
    bool sumGame(string num) {
        int n = num.length();
        int cnt1 = 0;
        int cnt2 = 0;
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0 ;i<n/2 ; ++i){
            if(num[i]=='?') cnt1++;
            else sum1+=(num[i]-'0');
        }
        for(int i = n/2 ; i<n ; ++i){
            if(num[i]=='?') cnt2++;
            else sum2 += (num[i]-'0');
        }
        int diff = sum1 - sum2;
        int cnt_diff = cnt1 - cnt2;
        if(!cnt_diff) return (diff);
        if(!diff) return true;
        if(diff<=0 ^ cnt_diff<=0 == 0) return true;
        if((abs(cnt_diff)/2 + abs(cnt_diff)%2 )*9 > abs(diff)) return true;
        if((abs(cnt_diff)/2)*9 >= abs(diff)) return false;
        return true;
    }
};
```
