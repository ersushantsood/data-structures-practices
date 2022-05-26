package Trie;

public interface Trie<V> {
    default V getOrNull(CharSequence str)
    {
        return getOrDefault(str, null);
    }


    V getOrDefault(CharSequence str, V defaultValue);
}
