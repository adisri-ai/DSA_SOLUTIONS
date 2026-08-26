# Question 
You are given a binary string s and a positive integer k.

A substring of s is beautiful if the number of 1's in it is exactly k.

Let len be the length of the shortest beautiful substring.

Return the lexicographically smallest beautiful substring of string s with length equal to len. If s doesn't contain a beautiful substring, return an empty string.

A string a is lexicographically larger than a string b (of the same length) if in the first position where a and b differ, a has a character 
strictly larger than the corresponding character in b.

For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the fourth character, and `d` is greater than `c`.  
# Constraints   
```
1 <= s.length <= 100
1 <= k <= s.length
```
# Code (in C++)  
```
class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        int n = s.length();
        int curr = INT_MAX;
        string ans = "";
        for(int i = 0 ; i<n ; ++i){
            int cnt = 0;
            int j = i;
            for( ; j<n ; ++j){
                if(s[j]=='1') cnt++;
                if(cnt==k) break;
            }
            if(cnt==k && j-i+1<curr){
                curr = j-i+1;
                ans = s.substr(i , j-i+1);
            }
            else if(cnt==k && j-i+1==curr){
                ans = min(ans , s.substr(i , j-i+1));
            }
        }
        return ans;
    }
};
```
