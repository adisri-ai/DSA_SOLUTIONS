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
