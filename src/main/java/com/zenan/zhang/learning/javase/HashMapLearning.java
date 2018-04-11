package com.zenan.zhang.learning.javase;



import java.io.Serializable;
import java.util.*;

/**
 * @author zhang zenan
 *         Created by zhang zenan on 2017/10/29.
 *         翻译 hash Table 注释
 *         hash table 实现map接口。此实现提供了所有可选的map操作，并允许null值、null key。（HashMap与HashTable基本一致，除了允许null和线程不同步）
 *         HashMap 不保证map有序，特别是，它不保证该顺序恒久不变。
 *         <p>
 *         假设hash function 将数据适当的分散到桶里，HashMap 执行put、get操作的时间是常量值（可以理解为O(1)?）。使用迭代器遍历所有数据的性
 *         能跟hashMap的桶（Bucket）的数量有直接关系，为了提高遍历性能，不能设置比较大的容量或者加载因子（load factor）过低。
 *         <p>
 *         HastMap的实例有两个参数影响它的性能：初始容量和加载因子。容量是Hash table中桶的数量，初始容量是Hash table 创建时的容量。
 *         加载因子是hash table在其容量自增时间可达到多满的尺度。当hash table 中的数量超过加载因子与当前容量的乘积时,hash table将会再次
 *         哈希（rehashed 重建内部数据结构),从而hash table 将具有大约两倍的桶数。
 *         <p>
 *         通常，默认加载因子（0.75）是在时间和空间上的一种折中。加载因子过高减少了空间开销，但增加了查询成本（大多数HashMap操作（get、put）中都反映了这一点）
 *         在设置初始容量时应该考虑加载因子和它所需的容量，以便减少再次哈希（rehash）的操作次数。如果初始容量大于最大条数处理加载因子，则不会再次哈希。
 *         <p>
 *         如果有很多数据要存储在HashMap中，则相对于需要执行自动再次哈希操作以增大表容量来说，设置足够大的初始容量使得映射关系更能有效的存储。
 *         <p>
 *         注意：此实现不是同步的。如果多个线程在同时访问一个Hasp map,而且其中至少一个线程修改了这个map的结构。则它必须保持外部同步。（结构修改指：
 *         add、delete一个或多个映射关系，仅改变实例已包含键值包含在内）一般在多线程操作hash map时，要使用同步对象封装map.
 *         <p>
 *         如果不封装HashMap，可以使用 Collections.synchronizedMap 方法封装HashMap。最后在创建时完成这一操作，以防对map进行意外的非同步访问。
 *         示例： Map m = Collcetions.syncHronizedMap(new HashMap(...));
 *         <p>
 *         由所有此类的“集合视图方法”都是“fail-fast": 在迭代器创建后，如果结构上进行了修改，除了自身remove方法外，其他任何时间任何方式的修
 *         改都会抛出ConCurrentModeficationException.因此，面对并发的修改，迭代器会快速而干净的失败，而不是在未来不确定的时间冒着任意的非确定
 *         行为。
 *         <p>
 *         注意：fail-fast不能得到保证，一般来说，存在非同步并发修改时，不可能做出任何坚决保证。fail-fast 迭代器尽可能抛出ConcurrentModificationException。
 *         因此，编写依赖于此异常的程序的做法是错误的，正确做法：fail-fast应该仅用于检测程序的bug
 *         <p>
 *         <p>
 *         此类是java Collections Framework 成员
 */
public class HashMapLearning<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jdk1.8
     *
     */

    /**
     * the default initial capacity - MUST be a power of two.
     * 默认容量，必须是2的幂
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;// 2的4次方

    /**
     * 最大容量，如果一个较高的值带有参数的构造函数隐式指定，必须小于等于
     */
    static final int MAXIMUM_CAPACITY = 1 << 30; //2的30次方

    /**
     * 默认加载因子，当构造函数中没有指定加载因子时。
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * An empty table instance to share when the table is not inflated;
     * inflate 飞涨的，膨胀的；言过其实的
     */
    static final Entry<?, ?>[] EMPTY_TABLE = {};

    /**
     * The table,resized as necessary. Length MUST Always be a power of two;
     * 表大小需要重置，切长度必须的2的幂
     */
    transient Entry<K, V>[] table = (Entry<K, V>[]) EMPTY_TABLE;

    /**
     * The number of key-value mapping contained in this map.
     * map大小
     */
    transient int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     * 阈值 容量*装载因子
     */
    int threshold;

    /**
     * The load factor for the hash table.
     * 加载因子
     */
    final float loadFactor;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mapping in the HashMap or
     * otherwise modify its internal structural (e.g.,rehash). This field is used to make iterators
     * on Collection-views of the HashMap fail-fast.  (See ConcurrentModificationException).
     * 结构修改次数
     */
    transient int modCount;

    /**
     * The default threshold of map capacity above which alternative hashing is used for String keys.
     * Alternative hashing reduces the incidence of collisions due to weak hash code calculation for
     * String keys.
     * This value may be overridden by defining the system property.A property value of forces
     * alternative hashing to be used at all times whereas value ensures that alternative hashing
     * is never used.
     */
    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;

    /**
     * holds values which can't be initialized until after VM is booted.
     */
    private static class Holder {
        /**
         * Table capacity above which to switch to use alternative hashing.
         */
        static final int ALTERNATIVE_HASHING_THRESHOLD;

        static {
            String altThreshold = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction("jdk.map.althashing.threshold")
            );

            int threshold;
            try {
                threshold = (null != altThreshold) ? Integer.parseInt(altThreshold) : ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }
                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch (IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold' ", failed);
            }
            ALTERNATIVE_HASHING_THRESHOLD = threshold;
        }
    }

    /**
     * A randomizing value associated with  this instance that is applied to hash code of keys
     * to make hash collections harder to find. If 0 then alternative hashing is disabled.
     * 一个随机的值
     * 如果hashSeed 为 0，这不可用
     */
    transient int hashSeed = 0;

    /**
     * Constructs an empty HashMap with the specified initial capacity and load factor.
     * Argument 变元 (函数的)自变数
     */
    public HashMapLearning(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }

    /**
     * Constructs an empty HashMap with the specified initial capacity and the default load factor (0.75).
     * 默认加载因子 0.75
     */
    public HashMapLearning(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty HashMap with the default initial capacity (16) and the default load factor (0.75).
     * 默认容量16，默认加载因子0.75
     */
    public HashMapLearning() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new HashMap with the same mappings as the specified(指定的) Map.The HashMap is created
     * with default load factor(0.75) and an initial capacity sufficient to hold the mappings in the specified.
     * <p>
     * 如果map为空 则报空指针异常（NullPointerException）
     */
    public HashMapLearning(Map<? extends K, ? extends V> m) {
        //调用默认构造函数，如果map大小大于12 （默认装载因子与默认容量的乘积，16*0.75=12），
        //默认容量取 map.site()/0.75,否则取16
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);

        //扩容
        inflateTable(threshold);

        putAllForCreate(m);
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >=0 假设number大于0
        // 返回大于number的最小的2的幂
        return number >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    /**
     * inflates the table
     * 扩容
     */
    private void inflateTable(int toSize) {
        //find a power of 2>= toSite 返回大于toSize的最小2的幂;ru
        int capacity = roundUpToPowerOf2(toSize);

        //阈值为 容量与加载因子的乘积
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        table = new Entry[capacity];

        initHashSeedAsNeeded(capacity);
    }

    //internal utilities

    /**
     * Initialization hook for subclass. This method is called is all constructors and
     * pseudo-constructors (clone ,readObject) after HashMap has bees initialized but before
     * and entries have been inserted. (In the absence of this method, readObject would require
     * explicit knowledge of subclasses.)
     */
    void init() {

    }

    /**
     * Initialize the hashing mask value. We defer initialization until we really need it.
     * 在需要时初始化
     */
    final boolean initHashSeedAsNeeded(int capacity) {
        boolean currentAltHashing = hashSeed != 0;
        boolean useAltHashing = sun.misc.VM.isBooted()
                && (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean switching = currentAltHashing ^ useAltHashing; // ^ : 异或
        if (switching) {
            hashSeed = 0;//useAltHashing ? sun.misc.Hashing.randomHashSeed(this) : 0;
        }
        return switching;
    }

    /**
     * retrieve object hahs code and applies a supplemental hash function to the result hash,which
     * defends against poor quality hash functions. This is critical because HashMap uses power-of-two
     * length hash tables, that otherwise encounter collisions for hashCodes that do not differ in
     * lower bits . Note: Null keys always map to hash 0,thus index 0.
     */
    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            return h;//sun.misc.Hashing.stringHash32((String) k);
        }
        h ^= k.hashCode();

        //This function ensures that hashCodes that differ only by constant multiples at each bit
        //position have a bounded number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hash code h.
     * 相当于对length取余
     */
    static int indexFor(int h, int length) {
        //assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length - 1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(Object key) {
        if (key == null) {
            return getForNullKey();
        }
        Entry<K, V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    /**
     * Offloaded version of get() to look up null keys. Null keys map to index 0.
     * This null case is split out into separate methods for the sake of performance in the
     * two most commonly used operations (get and put), but incorporated with conditionals in others.
     *
     * @return
     */
    private V getForNullKey() {
        if (size == 0) {
            return null;
        }
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            return e.value;
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry associated with the specified key in the HashMap.
     * Returns null if the HashMap contains no mapping for the key.
     *
     * @param key
     * @return
     */
    final Entry<K, V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
        if (key == null) {
            return putForNullKey(value);
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
        addEntry(hash, key, value, i);
        return null;
    }

    /**
     * Offload version of put for null keys
     */
    private V putForNullKey(V value) {
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }

    private void putForCreate(K key, V value) {
        int hash = null == key ? 0 : hash(key);
        int i = indexFor(hash, table.length);

        /**
         * Look for preexisting entry for key. This will never happen for clone or deserialize.
         * It will only happen for construction if the input Map is a sorted map whose ordering
         * is inconsistent w/ equals.
         */
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) {
                e.value = value;
                return;
            }
        }
        createEntry(hash, key, value, i);
    }

    /**
     * This method is used instead of put constructors and pseudoconstructors(clone,readObject).
     * It does not resize the table, check for comodification,etc. It calls createEntry rather
     * than addEntry.
     * 替代put方法
     */
    private void putAllForCreate(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            putForCreate(e.getKey(), e.getValue());
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;

        V value;

        Entry<K, V> next;

        int hash;

        Entry(int h, K k, V v, Entry<K, V> n) {
            value = v;
            key = k;
            next = n;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        void recordAccess(HashMapLearning<K, V> m) {
        }

        void recordRremoval(HashMapLearning<K, V> m) {
        }
    }

    /**
     * Adds a new entry with the specified key,value and hash code to the specified bucket.
     * It is the responsibility of this method to resize the table if appropriate.
     * <p>
     * Subclass overrides this to alter the behavior of put method.
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        //site大于阈值
        if ((size >= threshold) && (null != table[bucketIndex])) {
            //扩容到二倍，新表
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    /**
     * Like addEntry except that this version is used when creating entries as part of Map construction or
     * "pseudo-construction"(cloning,deserialization).This version needn't worry about resizing the table.
     */
    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
        size++;
    }

    /**
     * Rehashes the contents of this map into a new array with a larger capacity.This method is called
     * automatically when the number of keys in this map reached its threshold.
     * <p>
     * If current capacity  is MAXIMUM_CAPACITY ,this method does not resize the map, but sets threshold
     * to Integer.MAX_VALUE. This has the effect of preventing future calls.
     * 用一个大容量的map,对原来的map再哈希；当keys的数量接近阈值时被调用。
     */
    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        table = newTable;
        threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K, V> e : table) {
            while (null != e) {
                Entry<K, V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    /**
     * Copies all of the mapping from the specified map to this map. These mappings will
     * replace any mappings that this map had for any of any of the keys currently
     * in the specified map.
     */

    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0) {
            return;
        }
        if (table == EMPTY_TABLE) {
            inflateTable((int) Math.max(numKeysToBeAdded * loadFactor, threshold));
        }
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int) (numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY) {
                targetCapacity = MAXIMUM_CAPACITY;
            }
            int newCapacity = table.length;
            while (newCapacity < targetCapacity) {
                newCapacity <<= 1;
            }
            if (newCapacity > table.length) {
                resize(newCapacity);
            }
        }
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    public boolean containsValues(Object value){
       if(value==null){
           return true;
       }
       return false;
    }

    private boolean containsNullValue(){
        return true;
    }



    public V remove(Object key) {
        Entry<K, V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    final Entry<K, V> removeEntryForKey(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        Entry<K, V> prev = table[i];
        Entry<K, V> e = prev;
        while (e != null) {
            Entry<K, V> next = e.next;
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) {
                modCount++;
                size--;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
                e.recordRremoval(this);
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }


    private transient Set<Map.Entry<K, V>> entrySet = null;

    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet0();
    }

    private Set<Map.Entry<K, V>> entrySet0() {
        Set<Map.Entry<K, V>> es = entrySet;
        return es;
    }

    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<Long, Long>();
        map.put(1L, 1L);
        Map map1 = new HashMap(map);
        String k = "149";
        int h = 0;
        h ^= k.hashCode();

        //This function ensures that hashCodes that differ only by constant multiples at each bit
        //position have a bounded number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        int x = h ^ (h >>> 7) ^ (h >>> 4);
        System.out.print(map1);
    }

}
