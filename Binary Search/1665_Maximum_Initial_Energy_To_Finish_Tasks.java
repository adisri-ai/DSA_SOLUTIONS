// Question
//You are given an array tasks where tasks[i] = [actuali, minimumi]:

//actuali is the actual amount of energy you spend to finish the ith task.
//minimumi is the minimum amount of energy you require to begin the ith task.
//For example, if the task is [10, 12] and your current energy is 11, you cannot start this task. However, if your current energy is 13, you can complete this task, and your energy will be 3 after finishing it.

//You can finish the tasks in any order you like.

//Return the minimum initial amount of energy you will need to finish all the tasks.
class Solution {
    private boolean check(int [][] tasks , int mid){
        int curr = mid;
        boolean flag = true;
        for(int i = 0 ; i<tasks.length ; ++i){
            if(tasks[i][1] > curr){
                flag = false;
                break;
            }
            curr-= tasks[i][0];
        }
        if(flag) return true;
        curr = mid;
        for(int i = tasks.length-1 ; i>=0 ; --i){
            if(tasks[i][1] > curr){
                return false;
            }
            curr-=tasks[i][0];
        }
        return true;
    }
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks , (a , b) -> (b[1]-b[0])-(a[1]-a[0]));
        int n = tasks.length;
        int x = 0;
        for(int i = 0 ; i<n ; ++i){
            x+=tasks[i][1];
        }
        int start= 0;
        int end = x;
        int ans = -1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(check(tasks , mid)){
                ans = mid;
                end = mid-1;
            }
            else start = mid+1;
        }
        return ans;
    }
}
//Explanation 
// We first sort the array in decreasing order of difference between required and actual energy this is because tasks with higher extra energy shall be performed
// first. Then we perform binary search with initially lower bound as 0 and upper bound as sum of all required energies.
