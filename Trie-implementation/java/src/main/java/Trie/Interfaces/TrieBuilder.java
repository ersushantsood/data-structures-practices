package Trie.Interfaces;

public interface TrieBuilder<V> {
    static <V> TrieBuilder.Builder<V> newBuilder(){
        return null;
    }

    interface Builder<V>{
        Builder<V> put(CharSequence str, V value);
        Trie<V> build();
    }
}
