
class Node{
    Node[] arr;
    boolean flag;
    public Node(){
        this.arr = new Node[26];
        for(int i = 0 ; i<26 ; ++i){
            this.arr[i] = null;
        }
        this.flag = false;
    }
}
class Trie {
    Node root;
    public Trie() {
        this.root = new Node();
    }
    
    public void insert(String word) {
        Node curr = this.root;
        for(int i = 0 ; i<word.length() ; ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.arr[idx]!=null){
                curr = curr.arr[idx];
            }
            else{
                curr.arr[idx]  = new Node();
                curr = curr.arr[idx];
            }
        }
        curr.flag = true;
        return;
    }
    
    public boolean search(String word) {
        Node curr = this.root;
        for(int i = 0 ; i<word.length(); ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.arr[idx]==null) return false;
            curr = curr.arr[idx];
        }
        return curr.flag;
    }
    
    public boolean startsWith(String word) {
         Node curr = this.root;
        for(int i = 0 ; i<word.length(); ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.arr[idx]==null) return false;
            curr = curr.arr[idx];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
