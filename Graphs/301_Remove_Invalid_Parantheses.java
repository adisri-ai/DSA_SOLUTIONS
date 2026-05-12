//Question
//Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
//Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
class Solution {
    public boolean check(String s){
        int curr = 0;
        for(int i = 0 ; i<s.length() ; ++i){
            if(s.charAt(i)>='a' && s.charAt(i)<='z') continue;
            if(s.charAt(i)=='(') curr++;
            else if(curr==0) return false;
            else curr--;
        }
        if(curr!=0) return false;
        return true;
    }
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> q = new LinkedList<>();
        int ans = -1;
        HashMap<String , Boolean> vis = new HashMap<>();
        HashSet<String> l = new HashSet<>();
        q.add(s);
        vis.put(s , true);
        while(!q.isEmpty()){
            String temp = q.poll();
            if(temp.length()<ans) continue;
            if(check(temp)){
                if(ans==-1) ans = temp.length();
                if(temp.length() == ans) l.add(temp);
            }
            if(temp.length()>ans){
                if(temp.length()==0 && !vis.containsKey("")) q.add("");
                for(int  i = 0 ; i<temp.length() ; ++i){
                    if(temp.charAt(i)=='(' || temp.charAt(i)==')'){
                        StringBuilder sb = new StringBuilder(temp);
                        String s2 = sb.deleteCharAt(i).toString();
                        if(!vis.containsKey(s2)){
                            vis.put(s2 , true);
                            q.add(s2);
                        }
                    }
                }
            }
        }
        List<String> final_ans = new ArrayList<>();
        for(String k : l){
            final_ans.add(k);
        }
        return final_ans;
    }
}
// Explanation 
// For every string we eliminate one character either '(' or ')' if there is no answer found or if the length of the string is greater than the length of the ans
// We perform BFS traversal using queue and upon finding a valid string when there was no ans found or if the length of ans was equal to the length of the string
// we add it to the set. Finally, we add all the elements of the set to the string.
