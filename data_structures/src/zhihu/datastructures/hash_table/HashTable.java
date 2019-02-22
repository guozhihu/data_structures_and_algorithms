package zhihu.datastructures.hash_table;

import java.util.TreeMap;

/**
 * Author: zhihu
 * Description: 哈希表(采用链地址法)
 * Date: Create in 2019/2/22 10:49
 */
public class HashTable<K, V> {
    
    private static final int upperTol = 10; // 扩容的上限因子
    private static final int lowerTol = 2; // 缩容的下限因子
    private static final int initCapacity = 7; // 初始容量大小
    
    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;
    
    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();
    }
    
    public HashTable() {
        this(initCapacity);
    }
    
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            map.put(key, value);
            size++;
            
            if (size >= upperTol * M)
                resize(2 * M);
        }
    }
    
    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            
            if (size <= lowerTol * M && M > initCapacity)
                resize(M / 2);
        }
        return ret;
    }
    
    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");
        
        map.put(key, value);
    }
    
    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }
    
    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
    
    // 扩容或缩容
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();
        
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }
        
        this.hashtable = newHashTable;
    }
}
