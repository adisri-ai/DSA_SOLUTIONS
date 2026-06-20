# Question  
```
You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.

However, there are city restrictions on the heights of the new buildings:

The height of each building must be a non-negative integer.
The height of the first building must be 0.
The height difference between any two adjacent buildings cannot exceed 1.
Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.

It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.

Return the maximum possible height of the tallest building.
```
# Approach    
1. We sort the *restrictions* array based on index. 
2. We greedily start by taking the max height of the building with the lowest *maxHeight* height restriction.
3. Based on this we assign the *maxHeights* to the adjacent building until we encounter another restricted build.
4. We also take care that the height in the gap between a smaller and larger restricted bulilding is incremented such that the height constraint of the
   larger bulding is also satisfied. Hence we first increase and then decrease the height(if necessary) to statisfy the height constraint 
# Code
```
  
class Solution {
public:
    int maxBuilding(int n, vector<vector<int>>& restrictions) {
        restrictions.push_back({1 , 0});
        sort(restrictions.begin() , restrictions.end());
        vector<vector<int>>res_copy = restrictions;
        int m = restrictions.size();
        int ans= 0;
        set<pair<int,int>>st;
        for(int i = 0 ; i<m ; ++i){
            st.insert({restrictions[i][1] , i});
        }
        while(!st.empty()){
            int num = st.begin()->first;
            int idx = st.begin()->second;
            int pos = restrictions[idx][0];
            st.erase(*st.begin());
            if(idx){
                int prev = idx-1;
                int prev_num = restrictions[prev][1];
                int prev_pos = restrictions[prev][0];
                int gap = pos - prev_pos;
                int diff = prev_num - num;
                if(prev_num - num >=gap){
                    if(diff > gap){
                        st.erase({prev_num , prev});
                        st.insert({num + gap , prev});
                        restrictions[prev][1] = num+gap;
                        prev_num = num + gap;
                    }
                    diff = prev_num - num;
                }
            }
            if(idx+1<m){
                int prev = idx+1;
                int prev_num = restrictions[prev][1];
                int prev_pos = restrictions[prev][0];
                int gap = prev_pos - pos;
                int diff = prev_num - num;
                if(prev_num - num >= gap){
                    if(diff > gap){
                        st.erase({prev_num , prev});
                        st.insert({num + gap , prev});
                        prev_num = num + gap;
                        restrictions[prev][1] = num+gap;
                    }
                    diff = prev_num - num;
                }
            }
        }
        for(int i = 0 ; i<m ; ++i) st.insert({restrictions[i][1] , i});
        while(!st.empty()){
            int num = st.begin()->first;
            int idx = st.begin()->second;
            int pos = restrictions[idx][0];
            if(idx==m-1) ans = max(ans , num + n-pos);
            st.erase(*st.begin());
            if(idx){
                int prev = idx-1;
                int prev_num = restrictions[prev][1];
                int prev_pos = restrictions[prev][0];
                int gap = pos - prev_pos;
                int diff = prev_num - num;
                if(prev_num >= num){
                    if(diff > gap){
                        st.erase({prev_num , prev});
                        st.insert({num + gap , prev});
                        restrictions[prev][1] = min(restrictions[prev][1] , num+gap);
                        prev_num = num + gap;
                    }
                    diff = prev_num - num;
                    ans = max(ans , prev_num + (gap - diff)/2);
                }
            }
            if(idx+1<m){
                int prev = idx+1;
                int prev_num = restrictions[prev][1];
                int prev_pos = restrictions[prev][0];
                int gap = prev_pos - pos;
                int diff = prev_num - num;
                if(prev_num >= num){
                    if(diff > gap){
                        st.erase({prev_num , prev});
                        st.insert({num + gap , prev});
                        prev_num = num + gap;
                        restrictions[prev][1] = min(restrictions[prev][1] , num+gap);
                    }
                    diff = prev_num - num;
                    ans = max(ans , prev_num + (gap - diff)/2);
                }
            }
        }
        return ans;
    }
};
