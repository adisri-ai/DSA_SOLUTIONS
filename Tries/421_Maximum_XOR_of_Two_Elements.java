// Question  
//Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
// Explanation: 
// To maximize XOR starting from the most significant bit we try to make a number having 
// different bits as much as possible greedily for a given number 
// Hence here we iterate through the entire array and for each element try to find the 
// maximum possible XOR by traversing the Trie for every element and store the maximum answer obtained so far in a variable. 
class Node {

    Node[] arr;
    boolean flag;

    public Node() {
        this.arr = new Node[2];
        for (int i = 0; i < 2; ++i) {
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

    public void insert(String k) {

        Node curr = this.root;

        for (int i = 0; i < 32; ++i) {

            int c = k.charAt(i) - '0';

            if (curr.arr[c] == null) {
                curr.arr[c] = new Node();
            }

            curr = curr.arr[c];
        }

        curr.flag = true;
    }

    public int find_max(String k) {

        Node curr = this.root;

        int ans = 0;

        for (int i = 0; i < 32; ++i) {

            int bit = k.charAt(i) - '0';

            int opposite = 1 - bit;

            if (curr.arr[opposite] != null) {

                ans = (ans << 1) | 1;

                curr = curr.arr[opposite];
            }
            else {

                ans = (ans << 1);

                curr = curr.arr[bit];
            }
        }

        return ans;
    }
}

class Solution {

    public int findMaximumXOR(int[] nums) {

        Trie tr = new Trie();

        for (int num : nums) {

            String s = String.format(
                    "%32s",
                    Integer.toBinaryString(num)
            ).replace(' ', '0');

            tr.insert(s);
        }

        int ans = 0;

        for (int num : nums) {

            String s = String.format(
                    "%32s",
                    Integer.toBinaryString(num)
            ).replace(' ', '0');

            ans = Math.max(ans, tr.find_max(s));
        }

        return ans;
    }
}
