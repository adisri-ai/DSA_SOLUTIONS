# Question  
```
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.
```
# Constraints   
```
Constraints:

1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
```
# Approach  
1. For every edge individually we add it to the adjacency list and also increase the degree of the corresponding nodes by 1.
2. We maintain a visited array to check if the node is visited.
3. For every unvisited node we perform a BFS traversal and mark the nodes visited.
4. If all the nodes have the same degree equal to `n-1` where `n` is the number of nodes in the connected component we add this component to the answer.
5. Finally, we return the total number of such components.
# Code(in C++)  
```
class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<int>vis(n , 0);
        vector<int>deg(n , 0);
        vector<vector<int>>adj(n);
        for(auto edge : edges){
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
            deg[edge[0]]++;
            deg[edge[1]]++;
        }
        queue<int>q;
        int ans  = 0;
        for(int i = 0 ; i<n ; ++i){
            if(!vis[i]){
                q.push(i);
                int flag = 1;
                int k = deg[i];
                int cnt = 0;
                vis[i] = 1;
                while(!q.empty()){
                    int node = q.front();
                    cnt++;
                    if(deg[node]!=k) flag = 0;
                    q.pop();
                    for(auto x  :adj[node]){
                        if(!vis[x]) {q.push(x); vis[x] = 1;}
                    }
                }
                if(cnt==k+1) ans+=flag;
            }
        }
        return ans;
    }
};
```
