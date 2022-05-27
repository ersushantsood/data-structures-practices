import Trie.Trie;
import Trie.TrieBuilder;

public class Solution {

    public static void main(String[] args){

        Trie<Boolean> trie =  TrieBuilder.<Boolean>newBuilder()
                .put("abc", true)
                .put("abcd", true)
                .put("abcde", true)
                .put("abcdef", true)
                .put("abcdefg", true)
                .put("abcdefgh", true)
                .build();

        System.out.println("Check if nuke exists: "+trie.getOrNull("nuke"));

        System.out.println("Check if string: halloween exists: "+trie.getOrNull("halloween"));
    }
}
