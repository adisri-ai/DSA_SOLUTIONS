# Question  
```
You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
```
# Approach  
1. We first sort the array on the basis of finish time.
2. We then create an adjacency list based on this sorted array.
3. Finally, we perform a Dijkstra-like traversal on this resultant graph to obtain all the visited nodes.
# Code(in C++)  
```
class Solution {
public:
    int find(int node , vector<pair<int,int>>& v , int time , vector<int>& vis1){
        int start = 0;
        int end = v.size() - 1;
        int ans = v.size();

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(v[mid].second > time){
                ans = mid;
                end = mid - 1;
            }
            else if(v[mid].second == time){
                ans = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        return ans;
    }

    static bool fun(vector<int>& a , vector<int>& b){
        return a[2] < b[2]; 
    }

    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {

        sort(meetings.begin(), meetings.end(), fun);

        unordered_map<int , vector<pair<int,int>>> adj;

        for(auto &meeting : meetings){
            adj[meeting[0]].push_back({meeting[1], meeting[2]});
            adj[meeting[1]].push_back({meeting[0], meeting[2]});
        }

        vector<int> vis1(n, 1e9);

        priority_queue<
            pair<int,int>,
            vector<pair<int,int>>,
            greater<pair<int,int>>
        > q;

        q.push({0, 0});
        q.push({0, firstPerson});

        vis1[0] = 0;
        vis1[firstPerson] = 0;

        while(!q.empty()){

            int node = q.top().second;
            int time = q.top().first;
            q.pop();

            int idx = find(node, adj[node], time, vis1);

            for(int i = idx; i < (int)adj[node].size(); ++i){

                if(vis1[adj[node][i].first] > adj[node][i].second){
                    vis1[adj[node][i].first] = adj[node][i].second;
                    q.push({adj[node][i].second, adj[node][i].first});
                }
            }
        }

        vector<int> ans;

        for(int i = 0; i < n; ++i){
            if(vis1[i] != 1e9)
                ans.push_back(i);
        }

        return ans;
    }
};
