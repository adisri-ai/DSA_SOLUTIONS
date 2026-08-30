// Question  
//You are given a 0-indexed array of distinct integers nums.
//There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. 
//Your goal is to remove both these elements from the array.
//A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
//Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
int minimumDeletions(int* nums, int numsSize) {
    int ma = -1e9;
    int mi = 1e9+7;
    int idx1 = -1;
    int idx2 = -1;
    for(int i = 0 ; i<numsSize ; ++i){
        if(nums[i]>ma){
            ma = nums[i];
            idx1 = i;
        }
        if(nums[i]<mi){
            mi = nums[i];
            idx2 = i;
        }
    }
    if(idx1 > idx2){
       int temp = idx1;
       idx1 = idx2;
       idx2 = temp;
    }
    return fmin(idx1+1 + (numsSize-idx2) , fmin(idx2+1 , numsSize-idx1));
}
