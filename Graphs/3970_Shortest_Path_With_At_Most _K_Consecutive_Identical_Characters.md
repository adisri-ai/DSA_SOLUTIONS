# Question 
You are given an integer n representing the number of nodes in a directed weighted graph, numbered from 0 to n - 1. 

This is represented by a 2D integer array edges, where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with weight wi.

You are also given a string labels of length n, where labels[i] is the character assigned to node i, and an integer k.

Return the minimum total edge weight of a path from node 0 to node n - 1 such that the concatenation of the labels of the nodes along the path contains at

most k consecutive identical characters. If no valid path exists, return -1. 

# Code (in C++)  
```
class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges, string labels, int k) {
        vector<vector<pair<int,int>>>adj(n);
        for(auto& edge : edges){
            adj[edge[0]].push_back({edge[1] , edge[2]});
        }
        set<vector<long long>>st;
        vector<vector<int>>vis(n , vector<int>(k+1, INT_MAX));
        st.insert({0 , 1 , 0});
        vis[0][0] = 0;
        while(st.size()){
            long long sum = (*(st.begin()))[0];
            long long streak = (*(st.begin()))[1];
            long long node = (*(st.begin()))[2];
            if(node==n-1) return sum;
            st.erase(*(st.begin()));
            for(auto& temp : adj[node]){
                long long next = temp.first;
                long long dist = temp.second;
                int new_streak = (labels[node] == labels[next]) ? (streak + 1) : 1;
                if(new_streak > k || vis[next][new_streak] < sum + dist){
                    continue;
                }
                st.erase({vis[next][new_streak] , new_streak , next});
                vis[next][new_streak] = sum + dist;
                st.insert({sum + dist , new_streak , next});
            }
        }
        return -1;
    }
};
```
