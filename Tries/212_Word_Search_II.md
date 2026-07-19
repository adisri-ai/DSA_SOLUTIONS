# Question   
```
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
```
# Approach  
1. We store all the elements of the `words` array in a Trie.
2. At every element of the `board` we perform a dfs search. If the search points to a `nullptr`, then we simply return from the dfs search.
3. Finally we return the list of elements found in `words`.
# Code    
```
struct Node{
    vector<Node*> v;
    bool flag;

    Node(){
        v = vector<Node*>(26, nullptr);
        flag = false;
    }
};

class Trie{
public:

    Node* root;

    Trie(){
        root = new Node();
    }

    void insert(string word){

        Node* curr = root;

        for(char c : word){

            if(curr->v[c-'a']==nullptr)
                curr->v[c-'a'] = new Node();

            curr = curr->v[c-'a'];
        }

        curr->flag = true;
    }
};

class Solution {
public:

    void dfs(int i,
             int j,
             Node* curr,
             string word,
             vector<vector<char>>& board,
             vector<vector<int>>& vis,
             vector<string>& ans){

        char ch = board[i][j];

        if(curr->v[ch-'a']==nullptr)
            return;

        curr = curr->v[ch-'a'];

        word += ch;

        if(curr->flag){
            ans.push_back(word);
            curr->flag = false;        // avoid duplicates
        }

        vis[i][j] = 1;

        int dr[4]={-1,0,1,0};
        int dc[4]={0,1,0,-1};

        for(int k=0;k<4;k++){

            int ni=i+dr[k];
            int nj=j+dc[k];

            if(ni>=0 && ni<board.size() &&
               nj>=0 && nj<board[0].size() &&
               !vis[ni][nj]){

                dfs(ni,nj,curr,word,board,vis,ans);
            }
        }

        vis[i][j]=0;
    }

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {

        Trie tr;

        for(auto &word:words)
            tr.insert(word);

        int m=board.size();
        int n=board[0].size();

        vector<string> ans;

        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){

                vector<vector<int>> vis(m,vector<int>(n,0));

                dfs(i,j,tr.root,"",board,vis,ans);
            }
        }

        return ans;
    }
};
```
