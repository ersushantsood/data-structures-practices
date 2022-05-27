package Trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TrieContainer<V> implements Trie<V>, TrieBuilder<V> {

    private final TrieNode<V> root;

    private TrieContainer(TrieNode<V> root) {
        this.root = root;
    }

    @Override
    public V getOrDefault(CharSequence str, V defaultValue) {
        TrieNode<V> node = root;
        V lastMatch = defaultValue;

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            TrieNode<V> next = node.getNextNode(c);
            if (next == null) {
                return lastMatch;
            }
            node = next;
            lastMatch = next.value != null ? next.value : lastMatch;
        }
        return lastMatch;
    }

    // creating a static builder which will provide builder methods for trie
    static final class TrieBuilderImpl<V> implements Builder<V>{

        private final TrieNodeBuilder<V> rootNode = new TrieNodeBuilder<>();

        @Override
        public Builder<V> put(CharSequence str, V value) {

            // Initializing with the root node
            put(str, value, rootNode, 0);
            return this;
        }

        private void put(CharSequence str, V value, TrieNodeBuilder<V> nodeBuilder, int i) {
            if (str.length() == i) {
                nodeBuilder.nodeValue = value;
                return;
            }
            char c = str.charAt(i);
            TrieNodeBuilder<V> next = nodeBuilder.nodeBuilderChildren.computeIfAbsent(c, k -> new TrieNodeBuilder<>());
            // recursive building of trienodebuilders per char in string.
            put(str, value, next, i + 1);
        }

        @Override
        public Trie<V> build() {
            return new TrieContainer<V>(rootNode.buildNode());
        }
    }

    static final class TrieNodeBuilder<V> {
        // container for different TrieNodeBuilders which will be iterated to build all the
        //nodes at once when buildNode() is invoked on root node.
        final Map<Character, TrieNodeBuilder<V>> nodeBuilderChildren = new HashMap<>();
        V nodeValue;

        TrieNode<V> buildNode()
        {
            int subTreeSize = nodeBuilderChildren.size();
            char[] nodeChars = new char[subTreeSize];
            TrieNode<V>[] nodes = new TrieNode[subTreeSize];

            int index = 0;

            // sort the keys in the children map and then build iterator on the keys.
            Iterator<Map.Entry<Character, TrieNodeBuilder<V>>> nodeBuilderChildrenIt =
                    this.nodeBuilderChildren.entrySet().stream().sorted(Map.Entry.comparingByKey()).iterator();

            while (nodeBuilderChildrenIt.hasNext()){
                Map.Entry<Character, TrieNodeBuilder<V>> mapEntry = nodeBuilderChildrenIt.next();
                nodeChars[index] = mapEntry.getKey();

                // recursively building of all the children trie nodes.
                nodes[index++] = mapEntry.getValue().buildNode();
            }

            return new TrieNode<V>(nodeChars, nodes, nodeValue);
        }
    }
}

