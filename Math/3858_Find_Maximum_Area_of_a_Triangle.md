# Question  
You are given a 2D array coords of size n x 2, representing the coordinates of n points in an infinite Cartesian plane.

Find twice the maximum area of a triangle with its corners at any three elements from coords, such that at least one side of this triangle is parallel to the 

x-axis or y-axis. Formally, if the maximum area of such a triangle is A, return 2 * A.

If no such triangle exists, return -1.

Note that a triangle cannot have zero area.  
# Code  
```  

class Solution {
public:
    long long maxArea(vector<vector<int>>& coords) {
        int n = coords.size();

        unordered_map<int,int> x_min, x_max;
        unordered_map<int,int> y_min, y_max;

        for(int i = 0; i < n; ++i) {
            int x = coords[i][0];
            int y = coords[i][1];

            if(x_min.find(y) == x_min.end()) x_min[y] = x;
            else x_min[y] = min(x_min[y], x);

            if(x_max.find(y) == x_max.end()) x_max[y] = x;
            else x_max[y] = max(x_max[y], x);

            if(y_min.find(x) == y_min.end()) y_min[x] = y;
            else y_min[x] = min(y_min[x], y);

            if(y_max.find(x) == y_max.end()) y_max[x] = y;
            else y_max[x] = max(y_max[x], y);
        }

        set<int> max_x, max_y, min_x, min_y;

        for(int i = 0; i < n; ++i) {
            int x = coords[i][0];
            int y = coords[i][1];

            max_x.insert(-x);
            max_y.insert(-y);
            min_x.insert(x);
            min_y.insert(y);
        }

        long long ans = -1;

        for(int i = 0; i < n; ++i) {
            int x = coords[i][0];
            int y = coords[i][1];

            if(x_max[y] != x_min[y]) {
                max_y.erase(-y);
                min_y.erase(y);

                if(!max_y.empty()) {
                    int y1 = -(*max_y.begin());
                    long long prod = (long long)(x_max[y] - x_min[y]) * (y1 - y);
                    ans = max(ans, prod);
                }

                if(!min_y.empty()) {
                    int y2 = (*min_y.begin());
                    long long prod = (long long)(x_max[y] - x_min[y]) * (y - y2);
                    ans = max(ans, prod);
                }

                max_y.insert(-y);
                min_y.insert(y);
            }

            if(y_max[x] != y_min[x]) {
                max_x.erase(-x);
                min_x.erase(x);

                if(!max_x.empty()) {
                    int x1 = -(*max_x.begin());
                    long long prod = (long long)(y_max[x] - y_min[x]) * (x1 - x);
                    ans = max(ans, prod);
                }

                if(!min_x.empty()) {
                    int x2 = (*min_x.begin());
                    long long prod = (long long)(y_max[x] - y_min[x]) * (x - x2);
                    ans = max(ans, prod);
                }

                max_x.insert(-x);
                min_x.insert(x);
            }
        }

        return ans;
    }
};

```
