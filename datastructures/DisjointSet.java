package datastructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Weighted Quick-Union with Path Compression implementation of the Disjoint Set
 * Data Structure.
 * 
 * @author LucasBraganca
 *
 * @param <T>
 */
public class DisjointSet<T> {

    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> weight = new HashMap<>();

    public DisjointSet(Collection<T> objs) {
        for (T obj : objs) {
            parent.put(obj, obj);
        }
    }

    private T root(T obj) {
        while (obj != parent.get(obj)) {
            parent.put(obj, parent.get(parent.get(obj)));
            obj = parent.get(obj);
        }
        return obj;
    }

    public boolean find(T obj1, T obj2) {
        return root(obj1) == root(obj2);
    }

    public void union(T obj1, T obj2) {
        T rootObj1 = root(obj1);
        T rootObj2 = root(obj2);

        int weightRoot1 = weight.getOrDefault(rootObj1, 1);
        int weightRoot2 = weight.getOrDefault(rootObj2, 1);

        if (weightRoot1 < weightRoot2) {
            parent.put(rootObj1, rootObj2);
            weight.put(rootObj2, weightRoot1 + weightRoot2);
        } else {
            parent.put(rootObj2, rootObj1);
            weight.put(rootObj1, weightRoot1 + weightRoot2);
        }
    }
}
