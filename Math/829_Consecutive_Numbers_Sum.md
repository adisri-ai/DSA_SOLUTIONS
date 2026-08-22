# Question  
Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.    
# Constraints  
`1 <= n <= 1e9`
# Approach  
1. It can be mathematically proven that a number the factors `M` and `N` of a number `n` correspond to a unique sequence of consecutive positive integers
   summing up to `n` if `M` and `N` are of the form `(x-y)*(x+y+1)`. Where `x` and `y` are non-negative. 
2. This factorization can be reduced to `((x)*(x+1))/2 - (y*(y+1))/2` which denotes the difference between the prefix sum of natural numbers up to `x` and `y`.
3. Since the number of consecutive integers in the sequence must be more than 1 the difference between `x` and `y` must also be more than 1. 
# Code (in C++)    
```
class Solution {
public:
    int consecutiveNumbersSum(int n) {
        n*=2;
        long long k = sqrt(n);
        int ans = 0;
        for(long long i = 1 ; i<=k ; ++i){
            if(n%i == 0){
                long long M = (long long)(n)/i;
                long long N = i;
                long long x = M+N-1;
                long long y = M-1-N;
                ans+=(x%2==0 && y%2==0 && x>=0 && y>=0 && x-y>=2);
            }
        }
        return ans;
    }
};
```
