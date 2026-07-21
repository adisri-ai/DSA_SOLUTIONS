# Question    
```
You have a function printNumber that can be called with an integer parameter and prints it to the console.

For example, calling printNumber(7) prints 7 to the console.
You are given an instance of the class ZeroEvenOdd that has three functions: zero, even, and odd. The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A: calls zero() that should only output 0's.
Thread B: calls even() that should only output even numbers.
Thread C: calls odd() that should only output odd numbers.
Modify the given class to output the series "010203040506..." where the length of the series must be 2n.

Implement the ZeroEvenOdd class:

ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.
void zero(printNumber) Calls printNumber to output one zero.
void even(printNumber) Calls printNumber to output one even number.
void odd(printNumber) Calls printNumber to output one odd number.
 

Example 1:

Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
"0102" is the correct output.
Example 2:

Input: n = 5
Output: "0102030405"
 
```
Constraints:
```
1 <= n <= 1000
```
# Approach  
1. We maitain binary semaphore for each of `zero`,   `even` and   `odd`.
2. We accquire and release the locks according to the output sequence given in the question.
# Code  
```
class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {

        int original = 0;
        for(char c : s)
            if(c == '1') original++;

        s = "1" + s + "1";

        int n = s.length();
        vector<int> v;

        int i = 0;
        while(i < n){
            if(s[i] == '0'){
                int cnt = 0;
                while(i < n && s[i] == '0'){
                    cnt++;
                    i++;
                }
                v.push_back(cnt);
            }
            else{
                int cnt = 0;
                while(i < n && s[i] == '1'){
                    cnt++;
                    i++;
                }
                v.push_back(cnt);
            }
        }

        int ma = 0;
        for(int j = 2; j + 1 < v.size(); j += 2){
            ma = max(ma, v[j - 1] + v[j + 1]);
        }

        return original + ma;
    }
};
```
