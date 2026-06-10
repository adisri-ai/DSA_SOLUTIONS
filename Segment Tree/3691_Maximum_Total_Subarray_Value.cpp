// Question
//You are given an integer array nums of length n and an integer k.
//You must select exactly k distinct subarrays nums[l..r] of nums. 
//Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.
//The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
//The total value is the sum of the values of all chosen subarrays.
//Return the maximum possible total value you can achieve.
class Solution {
public:

    void build_max(int idx, int l, int r, vector<int>& nums, vector<int>& segtree
    ){
        if(l == r){
            segtree[idx] = nums[l];
            return;
        }
        int mid = (l + r) / 2;
        build_max(2 * idx + 1, l, mid, nums, segtree);
        build_max(2 * idx + 2, mid + 1, r, nums, segtree);
        segtree[idx] = max( segtree[2 * idx + 1], segtree[2 * idx + 2] );
    }
    void build_min(int idx, int l, int r, vector<int>& nums, vector<int>& segtree){
        if(l == r){
            segtree[idx] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        build_min(2 * idx + 1, l, mid, nums, segtree);

        build_min(2 * idx + 2, mid + 1, r, nums, segtree);

        segtree[idx] = min(  segtree[2 * idx + 1], segtree[2 * idx + 2]);
    }

    long long find_max(int idx, int l, int r, int ql, int qr, vector<int>& segtree){
        if(r < ql || l > qr) return INT_MIN;
        if(l >= ql && r <= qr) return segtree[idx];
        int mid = (l + r) / 2;
        return max( find_max( 2 * idx + 1, l, mid, ql, qr,segtree ), find_max(2 * idx + 2,mid + 1,r,ql,qr,segtree));
    }

    long long find_min(int idx, int l, int r, int ql, int qr, vector<int>& segtree){
        if(r < ql || l > qr) return INT_MAX;
        if(l >= ql && r <= qr) return segtree[idx];
        int mid = (l + r) / 2;
        return min( find_min(2 * idx + 1, l, mid, ql, qr, segtree), find_min(2 * idx + 2,mid + 1,r,ql,qr,segtree));
    }

    long long maxTotalValue(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> segtree_max(4 * n);
        vector<int> segtree_min(4 * n);
        build_max(0,0,n - 1,nums,segtree_max);
        build_min(0,0,n - 1,nums,segtree_min);

        unordered_map<int, unordered_map<int,int>> vis;

        long long ans = 0;

        priority_queue<vector<long long>> pq;
        pq.push({ find_max( 0,0,n-1, 0,n-1,segtree_max) - find_min(0,0,n-1,0,n-1,segtree_min),0,n-1});
        vis[0][n-1] = 1;
        int cnt = 0;
        while(cnt < k && !pq.empty()){
            vector<long long> temp = pq.top();
            pq.pop();
            ans += temp[0];
            int x = temp[1];
            int y = temp[2];
            if(x + 1 <= y && !vis[x + 1][y]){
                pq.push({find_max(0,0,n-1,x + 1,y,segtree_max)-find_min(0,0,n-1,x + 1,y,segtree_min),x + 1,y});
                vis[x + 1][y] = 1;
            }
            if(x <= y - 1 && !vis[x][y - 1]){
                pq.push({find_max(0,0,n-1,x,y - 1,segtree_max)-find_min(0,0,n-1,x,y - 1,segtree_min),x,y - 1});
                vis[x][y - 1] = 1;
            }
            cnt++;
        }
        return ans;
    }
};
