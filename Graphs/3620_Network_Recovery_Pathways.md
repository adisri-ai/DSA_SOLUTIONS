# Question  
```
You are given a directed acyclic graph of n nodes numbered from 0 to n − 1. This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a recovery cost of costi.

Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online. Nodes 0 and n − 1 are always online.

A path from 0 to n − 1 is valid if:

All intermediate nodes on the path are online.
The total recovery cost of all edges on the path does not exceed k.
For each valid path, define its score as the minimum edge‑cost along that path.

Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists, return -1.
```
# Constraints  
```
2 <= n <= 1e5
1 <= roads.length <= 1e5
roads[i].length == 3
1 <= ai, bi <= n
ai != bi
1 <= distancei <= 1e4
There are no repeated edges.
There is at least one path between 1 and n.
```
# Approach  
1. We perform binary search on ```minimum edge threshold``` from 1 to maximum edge length in the graph.
2. For each `threshold` we perform topological sort traversal to check if the path is possible since the graph is a DAG.
# Code (in C++)   
```
class Solution {
public:
    int n;

    bool check(int start, int end,
               vector<vector<pair<int,int>>>& adj,
               long long mid,
               long long k) {

        vector<long long> dist(n, LLONG_MAX);
        vector<int> indegree(n, 0);
        for(int u = 0; u < n; u++){
            for(auto [v, w] : adj[u]){
                if(w >= mid)
                    indegree[v]++;
            }
        }

        queue<int> q;
        for(int i = 0; i < n; i++)
            if(indegree[i] == 0)
                q.push(i);

        dist[start] = 0;

        while(!q.empty()){
            int u = q.front();
            q.pop();

            for(auto [v, w] : adj[u]){
                if(w < mid) continue;

                if(dist[u] != LLONG_MAX &&
                   dist[v] > dist[u] + w){
                    dist[v] = dist[u] + w;
                }

                indegree[v]--;
                if(indegree[v] == 0)
                    q.push(v);
            }
        }

        return dist[end] <= k;
    }

    int findMaxPathScore(vector<vector<int>>& edges,
                         vector<bool>& online,
                         long long k) {

        n = online.size();

        vector<vector<pair<int,int>>> adj(n);

        int hi = 0;

        for(auto &edge : edges){

            if(edge[0] != 0 && edge[0] != n-1 && !online[edge[0]])
                continue;
            if(edge[1] != 0 && edge[1] != n-1 && !online[edge[1]])
                continue;

            adj[edge[0]].push_back({edge[1], edge[2]});
            hi = max(hi, edge[2]);
        }

        int lo = 0;
        int ans = -1;

        while(lo <= hi){

            int mid = lo + (hi - lo) / 2;

            if(check(0, n-1, adj, mid, k)){
                ans = mid;
                lo = mid + 1;
            }
            else
                hi = mid - 1;
        }

        return ans;
    }
};
```
