# Question  
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, 

in the end, should remain as it is. You may not alter the values in the list's nodes, only nodes themselves may be changed.   

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
    ListNode* reverseKGroup(ListNode* head, int k) {
        if(k==1) return head;
        int count=0;
        ListNode* temp = head;
        vector<ListNode*> arr;
        while(temp!=NULL){
            arr.push_back(temp);
            temp = temp->next;
        }
        temp = head;
        int n = arr.size();
        int curr=1;
        int j=0;
        while(j<n-(n%k)){
            reverse(arr.begin()+j , arr.begin()+j+k);
            j = j+k;
        }
        for(int i=0 ; i<n-1 ; i++) arr[i]->next = arr[i+1];
        arr[n-1]->next = NULL;
        return arr[0];
    }
};
```
