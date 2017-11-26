package basic.collection;

import java.util.*;

/**
 * Created by Leo on 2017/10/24.
 */
public class HashMapTest {

    public static void main(String[] args) {
        /**
         * Map并没有实现Iterator
         */
        Map<String, String> testMap = new HashMap<String, String>();
        testMap.put( null, "test");
        testMap.put( null, "123");
        testMap.put( null, null);//覆盖之前的
        testMap.put("第一个元素", "元素A");
        testMap.put("第二个元素", "元素B");
        testMap.put("Aa", "键的hashCode相同Aa");
        testMap.put("BB", "键的hashCode相同BB");
        System.out.println("Aa".hashCode() == "BB".hashCode());//true
        System.out.println(testMap.get("Aa"));

//      HashMap的遍历

        /**
         * 第一种
         * Map.Entry是Map的一个内部接口
         * Map.Entry是Map声明的一个内部接口，此接口为泛型，定义为Entry<K,V>。
         * 它表示Map中的一个实体（一个key-value对）。接口中有getKey(),getValue方法。
         */
        Iterator iterator = testMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }

        /**
         * 第二种
         */
        Iterator keyIterator = testMap.keySet().iterator();
        while (keyIterator.hasNext()){
            Object key = keyIterator.next();
            Object value = testMap.get(key);
        }

        /**
         * 推荐
         */
        for (Map.Entry<String , String> entry:testMap.entrySet()){
            entry.getKey();
            entry.getValue();
        }

        for (String key:testMap.keySet()){
            testMap.get(key);
        }


        /**
         * 只遍历值
         */
        Collection collection = testMap.values();
        Iterator valueIterator = collection.iterator();
        while (valueIterator.hasNext()){
            Object value = valueIterator.next();
        }




//        System.out.println(testMap);

//        Map<Integer, String> hashTable = new Hashtable<Integer, String>();
//        hashTable.put(null, "123");//java.lang.NullPointerException

    }
}
