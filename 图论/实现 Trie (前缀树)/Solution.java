class Trie {
    class TrieNode {
        // 每个节点有26个子节点，分别对应 a-z
        TrieNode[] children;
        // 标记是否是一个完整的单词
        boolean isEnd;

        // 构造函数
        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curNode = root;
        for(int i=0;i<word.length();i++){
            char a = word.charAt(i);
            int index = a-'a';
            if(curNode.children[index]==null){
                curNode.children[index] = new TrieNode();
            }
            curNode = curNode.children[index];
        }
        curNode.isEnd = true;
    }

    private TrieNode searchNode(String s) {
        TrieNode current = root;
        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null; // 前缀不存在
            }
            current = current.children[index];
        }
        return current;
    }

    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */