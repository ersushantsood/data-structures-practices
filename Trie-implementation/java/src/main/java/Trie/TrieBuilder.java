package Trie;

public interface TrieBuilder<V> {
    static <V> TrieBuilder.Builder<V> newBuilder(){
        return new TrieContainer.TrieBuilderImpl<V>();
    }

    interface Builder<V>{
        Builder<V> put(CharSequence str, V value);
        Trie<V> build();
    }
}
