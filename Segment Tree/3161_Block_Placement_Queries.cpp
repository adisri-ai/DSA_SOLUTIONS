// Question
//There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
//You are given a 2D array queries, which contains two types of queries:
//For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that there is no obstacle at distance x when the query is asked.
//For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
//Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.
class Solution {
public:
    vector<int> seg;
    int MAXX = 50005;
    void update(int idx, int l, int r, int pos, int val) {
        if(l == r) {
            seg[idx] = val;
            return;
        }
        int mid = (l + r)/2;
        if(pos <= mid) update(2 * idx + 1, l, mid, pos, val);
        else update(2 * idx + 2, mid + 1, r, pos, val);
        seg[idx] = max(seg[2 * idx + 1], seg[2 * idx + 2]);
    }
    int query(int idx, int l, int r, int ql, int qr) {
        if(qr < l || r < ql) return 0;
        if(ql <= l && r <= qr) return seg[idx];
        int mid = (l + r)/2;
        return max(
            query(2 * idx + 1, l, mid, ql, qr),
            query(2 * idx + 2, mid + 1, r, ql, qr)
        );
    }

    vector<bool> getResults(vector<vector<int>>& queries) {
        int mx = 0;
        for(auto &q : queries)
            mx = max(mx, q[1]);
        MAXX = mx + 2;
        seg.resize(4 * MAXX, 0);
        set<int> obs;
        obs.insert(0);
        obs.insert(MAXX);
        update(0, 0, MAXX, MAXX, MAXX);
        vector<bool> ans;
        for(auto &q : queries) {
            if(q[0] == 1) {
                int x = q[1];
                auto it = obs.upper_bound(x);
                int r = *it;
                --it;
                int l = *it;
                obs.insert(x);
                update(0, 0, MAXX, r, r - x);
                update(0, 0, MAXX, x, x - l);
            }
            else {

                int x = q[1];
                int sz = q[2];

                int best = query(0, 0, MAXX, 0, x);

                auto it = obs.upper_bound(x);

                --it;

                best = max(best, x - *it);

                ans.push_back(best >= sz);
            }
        }

        return ans;
    }
};
