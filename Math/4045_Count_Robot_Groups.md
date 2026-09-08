# Question  
You are given a strictly increasing integer array position, where position[i] is the initial position of the ith robot at time t = 0.

You are also given an integer array speed, where speed[i] is the constant speed of the ith robot in units per second, and an integer distance.

Time is continuous and measured in seconds. A robot or group with speed v moves v * t units to the right over any interval of t seconds.

Whenever the distance between two robots or groups becomes at most distance, they merge into a single group.

If multiple robots or groups satisfy the merging condition at the same time, all merges happen simultaneously. In particular, every connected collection of 

robots or groups whose consecutive positions differ by at most distance merges into one group.

After a merge, the resulting group takes the current position and speed of the rightmost robot in that group. Once merged, robots never separate.

Return the number of groups remaining after all possible merges have occurred.    
# Constraints 
```
1 <= position.length == speed.length <= 1e5
1 <= position[i], speed[i], distance <= 1e9
position is strictly increasing.
```
# Approach  
1. We use backward iteration to store the rightmost speed of each group.
2. We merge if the speed of the smaller index is greater or distance is shorter than `distance`.
# Code (in C++)  
```
class Solution {
public:
    int countGroups(vector<int>& position, vector<int>& speed, int distance) {
        int n = position.size();
        int i = n-1;
        int ans = 0;
        int time = 0;
        while(i>=0){
            int j = i-1;
            while(j>=0 && (speed[j]>speed[i] || position[j+1] - position[j] <= distance)) --j;
            ans++;
            i = j;
        }
        return ans;
    }
};
```
