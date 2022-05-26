package Trie;

import java.util.Arrays;

public final class TrieNode<T> {
    final char[] chars;
    final TrieNode<T>[] children;
    final T value;

    TrieNode(char[] chars, TrieNode<T>[] children, T value){
        this.chars = chars;
        this.children = children;
        this.value = value;
    }

    TrieNode<T> getNextNode(char c){
        // binary search can be used as Nodes are sorted in Trie nodes
        int index = Arrays.binarySearch(chars, c);
        if (index < 0){
            return null;
        }
        return children[index];
    }
}
