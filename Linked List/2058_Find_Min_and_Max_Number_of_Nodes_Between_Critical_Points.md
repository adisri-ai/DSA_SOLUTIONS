# Question  
A critical point in a linked list is defined as either a local maxima or a local minima.
A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
Given a linked list head, return an array of length 2 containing `[minDistance, maxDistance]` where minDistance is the minimum distance between any two 
distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, 
return `[-1, -1]`.  
# Code (in C++)  
```
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        int prev_point = -1;
        int prev_greater = 0;
        int prev_smaller = 0;
        ListNode* prev = head;
        int first_point = -1;
        int minDistance = -1;
        int maxDistance = -1;
        ListNode* temp = head->next;
        int i = 0;
        while(temp){
            int greater = temp->val > prev->val;
            int smaller = temp->val < prev->val;
            if((smaller && prev_greater) || (greater && prev_smaller)){
                if(prev_point==-1) {first_point = i-1;}
                else{
                    maxDistance = i - first_point-1;
                    minDistance = (minDistance == -1) ? (i-prev_point-1) : min(minDistance, i - prev_point-1);
                }
                prev_point = i-1;
            }
            prev_greater = greater;
            prev_smaller = smaller;
            prev = temp;
            temp = temp->next;
            ++i;
        }
        return {minDistance , maxDistance};
    }
};
```
