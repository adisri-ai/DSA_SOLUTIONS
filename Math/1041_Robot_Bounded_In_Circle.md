# Question  
On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:

The north direction is the positive direction of the y-axis.
The south direction is the negative direction of the y-axis.
The east direction is the positive direction of the x-axis.
The west direction is the negative direction of the x-axis.
The robot can receive one of three instructions:

"G": go straight 1 unit.
"L": turn 90 degrees to the left (i.e., anti-clockwise direction).
"R": turn 90 degrees to the right (i.e., clockwise direction).
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.  

# Approach  
1. It can be proven that any string `instruction` will repeat the same sequence after 3 iterations of the string.
2. Hence if the coordinates are `(0,0)` at the end of 3 runs we return `true` indicating there is not net movement at the end of 3 runs. 
# Code (in C++)  
```
class Solution {
public:
    bool isRobotBounded(string instructions) {
        int x = 0;
        int y = 0;
        vector<pair<int,int>>directions = {{0 , 1} , {1 , 0} , {0 , -1} , {-1 , 0}};
        string temp = instructions;
        for(int i =0 ; i<3 ; ++i) instructions+=temp;
        int curr= 0;
        for(auto& instruction : instructions){
            if(instruction=='G'){
                x+=directions[curr].first;
                y+=directions[curr].second;
            }
            if(instruction=='L'){
                curr--;
                if(curr==-1) curr = directions.size()-1;
            }
            if(instruction=='R'){
                curr++;
                if(curr==directions.size()) curr = 0;
            }
        }
        return (!x && !y);
    }
};
```
