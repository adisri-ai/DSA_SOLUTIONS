
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
