# Question  
You are given an integer array nums.
For each integer x in nums, start with a string consisting of exactly x lowercase 'a' characters.
You may perform the following operation any number of times (including zero):
Choose two adjacent equal letters and replace them with the next letter in the alphabet.
For example, "aa" can be replaced with "b", and "bb" can be replaced with "c". The pair "zz" cannot be replaced.
For each x, determine the lexicographically largest string that can be obtained.
Return an array of strings where the ith string is the answer for nums[i].
A string a is lexicographically larger than a string b if, at the first position where they differ, a contains a letter that appears later in the alphabet than 
the corresponding letter in b. If the first min(a.length, b.length) characters are equal, the longer string is lexicographically larger.  

# Constraints  
`1 <= nums.length <= 1e5`
`1 <= nums[i] <= 1e8`
# Approach  
1. We traverse the binary form of every number bit-by-bit.
2. If any set bit is greater than the 25th index we append zz to the string.
3. After this we append the corresponding alphabet for each index of set bit to the string.
# Code  
```
class Solution {
public:
    vector<string> largestString(vector<int>& nums) {
        int n = nums.size();
        vector<string>ans;
        for(auto& num : nums){
            string temp = "";
            bool flag1 = 0;
            for(int j = 31 ; j>25 ; --j){
                if(num>>j & 1){
                    temp+="zz";
                    break;
                }
            }
            for(int j = 25 ; j>=0 ; --j){
                if(num>>j & 1) temp+=char('a' + j);
            }
            ans.push_back(temp);
        }
        return ans;
    }
};
```
