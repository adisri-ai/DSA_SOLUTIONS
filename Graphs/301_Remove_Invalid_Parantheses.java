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
