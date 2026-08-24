# Question  
```
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

```
# Code (in C++)  
```
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        vector<int>arr(26 , 0);
        int n1 = s1.length();
        int n2 = s2.length();
        if(n2<n1) return false;
        for(int i = 0 ; i<n1 ; ++i) arr[s1[i]-'a']++;
        vector<int>curr(26 , 0);
        for(int i = 0 ; i<n1 ; ++i) curr[s2[i]-'a']++;
        int start = 0;
        for(int j = n1 ; j<n2 ; ++j){
            if(curr == arr) return true;
            curr[s2[start]-'a']--;
            curr[s2[j]-'a']++;
            start++;
        }
        return curr == arr;
    }
};
```
