import Trie.Trie;
import Trie.TrieBuilder;

public class Solution {

    public static void main(String[] args){

        Trie<Boolean> trie =  TrieBuilder.<Boolean>newBuilder()
                .put("new.", true)
                .put("newton.", true)
                .put("nuke", true)
                .put("numeric", true)
                .put("number", true)
                .put("numb.", true)
                .put("java", true)
                .put("netizen", true)
                .put("germany", true)
                .put("potato", true)
                .put("rambo", true)
                .put("umbrella", true)
                .build();

        System.out.println("Check if nuke exists: "+trie.getOrNull("nuke"));

        System.out.println("Check if string: halloween exists: "+trie.getOrNull("halloween"));
    }
}
