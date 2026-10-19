# Question  
You are given an undirected tree of n nodes labeled from 0 to n - 1, represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates 

that there is an undirected edge between nodes ui and vi.

You are also given a string colors of length n, where colors[i] is one of 'R', 'B', or 'W', and denotes the color of node i.

A node chosen as root must satisfy both of the following conditions once the tree is oriented away from it:

Binary-tree property: Every node has at most two children.
Layered color pattern: All nodes at the same depth have the same color. Starting at depth 0, these colors follow one of the three repeating sequences:
R → B → W → R → B → W → ...
B → W → R → B → W → R → ...
W → R → B → W → R → B → ...
Return the index of any root that satisfies both conditions, or -1 if no such root exists.

A tree is a connected undirected graph with no cycles.

The depth of a node is the number of edges on the path from the root to that node. The root has depth 0. A child of a node is an adjacent node whose depth is one greater.

The degree of a node is the number of edges incident to it.

# Code (C++)  
``` 
class Solution {
public:
    int findColoredRoot(vector<vector<int>>& edges, string color) {
        int n = color.length();
        unordered_map<char , char>next;
        next['W'] = 'R';
        next['R'] = 'B';
        next['B'] = 'W';
        vector<vector<int>>adj(n);
        vector<int>parent(n , -1);
        for(auto& edge : edges){
            if(next[color[edge[0]]]!=color[edge[1]] && next[color[edge[1]]]!=color[edge[0]]) return -1;
            if(next[color[edge[0]]] == color[edge[1]]) {
                adj[edge[0]].push_back(edge[1]); 
                parent[edge[1]] = edge[0];
            }
            else {
                adj[edge[1]].push_back(edge[0]); 
                parent[edge[0]] = edge[1];
            }
        }
        for(int i = 0 ; i<n ; ++i){
            if(adj[i].size()){
               char col = color[adj[i][0]];
                int m = adj[i].size();
                for(int j = 0 ; j<m ; ++j){
                    if(color[adj[i][j]] != col) return -1;
                }
            }
        }
        vector<int>vis(n , 0);
        int cnt = 0;
        for(int i = 0 ; i<n ; ++i){
            if(parent[i]==-1){
                queue<int>q;
                q.push(i);
                vis[i] = 1;
                cnt++;
                while(!q.empty()){
                    int top = q.front();
                    q.pop();
                    if(adj[top].size()>2) return -1;
                    for(auto& child : adj[top]){
                        vis[child] = 1;
                        cnt++;
                        q.push(child);
                    }
                }
                if(cnt==n) return i;
                return -1;
            }
        }
        return -1;
    }
};  
```
