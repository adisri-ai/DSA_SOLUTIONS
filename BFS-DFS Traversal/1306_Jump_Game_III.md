# Question
```
Given an array of non-negative integers arr, you are initially positioned at 
start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
Notice that you can not jump outside of the array at any time.
```
# Approach  
1. We use BFS Traversal to solve this problem.
2. We maintain a visited array to avoid revisiting the same index.
3. If the index is univisted we push all the reachable states and mark it visited.
4. If we reach the index 0 we return *True* otherwise if the queue becomes empty we return *False*
# Code 
```
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] vis = new boolean[n];
        for(int i = 0 ; i<n ; ++i) vis[i] = false;
        vis[start] = true;
        while(!q.isEmpty()){
            int x = q.poll();
            if(arr[x]==0) return true;
            if(x+arr[x]<n && !vis[x+arr[x]]){
                vis[x+arr[x]] = true;
                q.add(x+arr[x]);
            }
            if(x-arr[x]>=0 && !vis[x-arr[x]]){
                vis[x-arr[x]] = true;
                q.add(x-arr[x]);
            }
        }
        return false;
    }
}
```
