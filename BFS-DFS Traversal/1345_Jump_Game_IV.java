class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        Queue<List<Integer>> q = new LinkedList<>();
        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        HashMap<Integer, Boolean> over = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (!mp.containsKey(arr[i])) {
                mp.put(arr[i], new ArrayList<>());
            }
            mp.get(arr[i]).add(i);
            if (!over.containsKey(arr[i])) {
                over.put(arr[i], false);
            }
        }
        boolean[] vis = new boolean[n];
        q.add(List.of(0, 0)); 
        vis[0] = true;
        while (!q.isEmpty()) {
            List<Integer> l = q.poll();
            int idx = l.get(0);
            int turn = l.get(1);
            if (idx == n - 1) return turn;
            if (idx - 1 >= 0 && !vis[idx - 1]) {
                vis[idx - 1] = true;
                q.add(List.of(idx - 1, turn + 1));
            }
            if (idx + 1 < n && !vis[idx + 1]) {
                vis[idx + 1] = true;
                q.add(List.of(idx + 1, turn + 1));
            }
            if (!over.get(arr[idx])) {
                List<Integer> temp = mp.get(arr[idx]);
                for (int j : temp) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.add(List.of(j, turn + 1));
                    }
                }
                over.put(arr[idx], true);
            }
        }
        return -1;
    }
}
