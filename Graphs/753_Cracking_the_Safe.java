// Question
// There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].

//The safe has a peculiar way of checking the password. When you enter in a sequence, 
//it checks the most recent n digits that were entered each time you type a digit.
//For example, the correct password is "345" and you enter in "012345":
//After typing 0, the most recent 3 digits is "0", which is incorrect.
//After typing 1, the most recent 3 digits is "01", which is incorrect.
//After typing 2, the most recent 3 digits is "012", which is incorrect.
//After typing 3, the most recent 3 digits is "123", which is incorrect.
//After typing 4, the most recent 3 digits is "234", which is incorrect.
//After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
//Return any string of minimum length that will unlock the safe at some point of entering it.


class Solution {
    private List<String> nodes;
    private String answer;
    private void generate(int n, int k, String curr) {
        if (n == 0) {
            nodes.add(curr);
            return;
        }
        for (int i = 0; i < k; ++i) {
            generate(n - 1, k, curr + i);
        }
    }
    private boolean dfs(String node,
                        HashMap<String, List<String>> adj,
                        HashSet<String> vis,
                        StringBuilder curr,
                        int total) {
        if (vis.size() == total) {
            answer = curr.toString();
            return true;
        }
        for (String nei : adj.get(node)) {
            if (!vis.contains(nei)) {
                vis.add(nei);
                curr.append(nei.charAt(nei.length() - 1));
                if (dfs(nei, adj, vis, curr, total))
                    return true;
                curr.deleteCharAt(curr.length() - 1);
                vis.remove(nei);
            }
        }
        return false;
    }
    public String crackSafe(int n, int k) {
        nodes = new ArrayList<>();
        generate(n, k, "");
        HashSet<String> set = new HashSet<>(nodes);
        HashMap<String, List<String>> adj = new HashMap<>();
        for (String s : nodes) {
            adj.put(s, new ArrayList<>());
            String suffix = s.substring(1);
            for (int d = 0; d < k; ++d) {
                String next = suffix + d;
                if (set.contains(next)) {
                    adj.get(s).add(next);
                }
            }
        }
        String start = nodes.get(0);
        HashSet<String> vis = new HashSet<>();
        vis.add(start);
        StringBuilder curr = new StringBuilder(start);
        dfs(start, adj, vis, curr, nodes.size());
        return answer;
    }
}
