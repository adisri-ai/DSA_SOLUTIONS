//Question
//You are given two arrays of strings wordsContainer and wordsQuery.
//For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. 
//If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is
//the smallest in length. If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.
//Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].
class Solution {
public:

    struct Node {

        int child[26];

        int bestLen;
        int bestIdx;

        Node() {

            memset(child, -1, sizeof(child));

            bestLen = INT_MAX;
            bestIdx = -1;
        }
    };

    vector<Node> trie;

    Solution() {

        trie.push_back(Node());
    }

    void insert(string &s, int idx) {

        int node = 0;

        int n = s.length();

        for(int i = 0; i < n; ++i) {

            if(n - i < trie[node].bestLen) {

                trie[node].bestLen = n - i;
                trie[node].bestIdx = idx;
            }

            int c = s[i] - 'a';

            if(trie[node].child[c] == -1) {

                trie[node].child[c] = trie.size();

                trie.push_back(Node());
            }

            node = trie[node].child[c];
        }

        if(0 < trie[node].bestLen) {

            trie[node].bestLen = 0;
            trie[node].bestIdx = idx;
        }
    }

    int query(string &s) {

        int node = 0;

        int ans = trie[0].bestIdx;

        for(char ch : s) {

            ans = trie[node].bestIdx;

            int c = ch - 'a';

            if(trie[node].child[c] == -1)
                return ans;

            node = trie[node].child[c];
        }

        return trie[node].bestIdx;
    }

    vector<int> stringIndices(
        vector<string>& wordsContainer,
        vector<string>& wordsQuery
    ) {

        for(auto &s : wordsContainer)
            reverse(s.begin(), s.end());

        for(auto &s : wordsQuery)
            reverse(s.begin(), s.end());

        for(int i = 0; i < wordsContainer.size(); ++i) {

            insert(wordsContainer[i], i);
        }

        vector<int> ans;

        for(auto &q : wordsQuery) {

            ans.push_back(query(q));
        }

        return ans;
    }
};
