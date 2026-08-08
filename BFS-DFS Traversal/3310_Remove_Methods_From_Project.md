# Question  
```
You are maintaining a project that has n methods numbered from 0 to n - 1.

You are given two integers n and k, and a 2D integer array invocations, where invocations[i] = [ai, bi] indicates that method ai invokes method bi.

There is a known bug in method k. Method k, along with any method invoked by it, either directly or indirectly, are considered suspicious and we aim to remove them.

A group of methods can only be removed if no method outside the group invokes any methods within it.

Return an array containing all the remaining methods after removing all the suspicious methods. You may return the answer in any order. 
If it is not possible to remove all the suspicious methods, none should be removed.
```
# Constraints  
```
1 <= n <= 1e5
0 <= k <= n - 1
0 <= invocations.length <= 2 * 1e5
invocations[i] == [ai, bi]
0 <= ai, bi <= n - 1
ai != bi
invocations[i] != invocations[j]
```
# Approach  
1. We convert this to a graph problem by creating an adjacency list for `invocations`.
2. We perform BFS traversal on the complete component starting from the node `k` and mark them visited in the array `vis`.
3. Now we traverse through all the edges again and if the two nodes of an edge contain 1 visited and 1 unvisited node we cannot remove `defective` methods.
4. Otherwise we can simply remove the visited nodes and return the final answer.
# Code(in C++)  
```
class Solution {
public:
    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<int>vis(n , 0);
        queue<int>q;
        q.push(k);
        vector<vector<int>>adj(n);
        for(auto& edge : invocations){
            adj[edge[0]].push_back(edge[1]);
        }
        vis[k] = 1;
        while(!q.empty()){
            int node = q.front();
            q.pop();
            for(auto& child : adj[node]){
                if(!vis[child]){
                    vis[child] = 1;
                    q.push(child);
                }
            }
        }
        bool flag = false;
        for(auto& edge : invocations){
            if(vis[edge[0]] ^ vis[edge[1]]) {flag = true; break;}
        }
        if(flag){
            vector<int>ans;
            for(int i = 0 ; i<n ; ++i) ans.push_back(i);
            return ans;
        }
        vector<int>ans;
        for(int i = 0 ; i<n ; ++i){
            if(!vis[i]) ans.push_back(i);
        }
        return ans;
    }
};
```
