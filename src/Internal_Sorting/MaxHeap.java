package Internal_Sorting;

import java.util.*;

public class MaxHeap<E> {
    //顺序存储结构存储堆
    private Object[] heapArray;
    //当前堆中元素数目
    private int currentSize;
    //最大元素数目
    private int maxSize;
    //默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    //具有默认初始容量的空数组(当加入第一个元素时，容量扩充为10）
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    //堆中元素的比较器
    private final Comparator<? super E> comparator;

    public MaxHeap() {
        heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        comparator = null;
    }

    public MaxHeap(Comparator<? super E> comparator) {
        heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        this.comparator = comparator;
    }

    public MaxHeap(Collection<? extends E> c) {
        heapArray = c.toArray();
        comparator = null;
        if ((currentSize = maxSize = heapArray.length) != 0) {
            // defend against c.toArray (incorrectly) not returning Object[]
            // (see e.g. https://bugs.openjdk.java.net/browse/JDK-6260652)
            if (heapArray.getClass() != Object[].class)
                heapArray = Arrays.copyOf(heapArray, maxSize, Object[].class);
        } else {
            // replace with empty array.
            this.heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        }
        BuildHeap();
    }

    public MaxHeap(Collection<? extends E> c, Comparator<? super E> comparator) {
        heapArray = c.toArray();
        this.comparator = comparator;
        if ((currentSize = maxSize = heapArray.length) != 0) {
            // defend against c.toArray (incorrectly) not returning Object[]
            // (see e.g. https://bugs.openjdk.java.net/browse/JDK-6260652)
            if (heapArray.getClass() != Object[].class)
                heapArray = Arrays.copyOf(heapArray, maxSize, Object[].class);
        } else {
            // replace with empty array.
            this.heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        }
        BuildHeap();
    }

    public MaxHeap(int[] array) {
        heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        comparator = null;
        currentSize = 0;
        maxSize = array.length;
        if (array.length > DEFAULT_CAPACITY) {
            grow(array.length);
            for (int e : array) {
                heapArray[currentSize++] = e;
            }
            BuildHeap();
        } else if (array.length > 0) {
            grow();
            for (int e : array) {
                heapArray[currentSize++] = e;
            }
            BuildHeap();
        }
    }

    public MaxHeap(double[] array) {
        heapArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        comparator = null;
        currentSize = 0;
        maxSize = array.length;
        if (array.length > DEFAULT_CAPACITY) {
            grow(array.length);
            for (double e : array) {
                heapArray[currentSize++] = e;
            }
            BuildHeap();
        } else if (array.length > 0) {
            grow();
            for (double e : array) {
                heapArray[currentSize++] = e;
            }
            BuildHeap();
        }
    }

    /**
     * 将已有元素构建为堆
     */
    public void BuildHeap() {
        currentSize=maxSize;
        for (int i = (maxSize - 2) >> 1; i >= 0; i--) {
            SiftDown(i);
        }
    }

    /**
     * 从position开始向上调整，通常用于向堆中插入元素
     *
     * @param position
     */
    private void SiftUp(int position) {
        if (comparator == null)
            SiftUpComparable(position);
        else
            SiftUpComparator(position);
    }

    private void SiftUpComparable(int position) {
        int child = position;
        int parent = parentIndex(child);
        Comparable<? super E> temp = (Comparable<? super E>) heapArray[position];
        while (child > 0 && temp.compareTo((E) heapArray[parent]) > 0) {
            heapArray[child] = heapArray[parent];
            child = parent;
            parent = parentIndex(parent);
        }
        heapArray[child] = temp;
    }

    private void SiftUpComparator(int position) {
        int child = position;
        int parent = parentIndex(child);
        Object temp = heapArray[position];
        while (child > 0 && comparator.compare((E) temp, (E) heapArray[parent]) > 0) {
            heapArray[child] = heapArray[parent];
            child = parent;
            parent = parentIndex(parent);
        }
        heapArray[child] = temp;
    }

    /**
     * 从position开始向下调整，通常用于删除元素时调整或建堆时
     *
     * @param position
     */
    private void SiftDown(int position) {
        if (comparator == null)
            SiftDownComparable(position);
        else
            SiftDownComparator(position);

    }

    private void SiftDownComparable(int position) {
        int parent = position;
        int leftchild = leftChildIndex(position);
        Comparable<? super E> temp = (Comparable<? super E>) heapArray[parent];//保存父节点值
        while (leftchild < currentSize) {
            if (leftchild + 1 < currentSize &&
                    ((Comparable<? super E>) heapArray[leftchild]).compareTo((E) heapArray[leftchild + 1]) < 0)
                leftchild++;
            if (temp.compareTo((E) heapArray[leftchild]) < 0) {
                heapArray[parent] = heapArray[leftchild];
                parent = leftchild;
                leftchild = leftChildIndex(parent);
            } else break;
        }
        heapArray[parent] = temp;
    }

    private void SiftDownComparator(int position) {
        int parent = position;
        int leftchild = leftChildIndex(parent);
        E temp = (E) heapArray[parent];
        while (leftchild < currentSize) {
            E c = (E) heapArray[leftchild];
            if (leftchild + 1 < currentSize &&
                    comparator.compare(c, (E) heapArray[leftchild + 1]) < 0)
                c = (E) heapArray[++leftchild];
            if (comparator.compare(temp, c) < 0) {
                heapArray[parent] = c;
                parent = leftchild;
                leftchild = leftChildIndex(parent);
            } else break;
        }
        heapArray[parent] = temp;
    }

    /**
     * 获得父节点索引
     *
     * @param position
     * @return
     */
    private int parentIndex(int position) {
        return (position - 1) >>> 1;
    }

    /**
     * 获得左子结点索引，无子结点返回-1
     *
     * @param position
     * @return
     */
    private int leftChildIndex(int position) {
        return (position << 1) + 1;
    }

    /**
     * 获得右子结点索引，无右节点返回-1
     *
     * @param position
     * @return
     */
    private int rightChildIndex(int position) {
        return position + 1 << 1;
    }

    /**
     * 向堆中插入新元素
     *
     * @param element
     */
    public void add(E element) {
        if (maxSize == heapArray.length) grow();
        maxSize++;
        heapArray[currentSize++] = element;
        SiftUp(currentSize - 1);
    }

    /**
     * 从堆中删除并返回堆顶元素
     *
     * @return
     */
    public E remove() {
        if (currentSize == 0) {
            return null;
        }
        Object root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        heapArray[currentSize] = root;
        SiftDown(0);
        return (E) root;
    }

    /**
     * 从堆中删除并返回指定下标处元素
     *
     * @param position
     * @return
     */
    public E remove(int position) {
        if (position < 0 || position >= currentSize)
            return null;
        if (position == 0)
            return remove();
        Object temp = heapArray[position];
        heapArray[position] = heapArray[--currentSize];
        heapArray[currentSize] = temp;
        if (comparator == null) {
            if (((Comparable<? super E>) heapArray[position]).compareTo((E) heapArray[parentIndex(position)]) > 0) {
                //如果当前结点大于父节点，向上调整
                SiftUp(position);
            } else SiftDown(position);
        } else {
            if (comparator.compare((E) heapArray[position], (E) heapArray[parentIndex(position)]) > 0)
                SiftUp(position);
            else SiftDown(position);

        }
        return (E) temp;
    }

    /**
     * 返回堆排序后的数组
     *
     * @return
     */
    public E[] sort() {
        while (currentSize > 0) {
            remove();
        }
        return (E[]) Arrays.copyOf(heapArray, maxSize);
    }

    /**
     * 以数组形式返回堆
     *
     * @return
     */
    public E[] getheap() {
        return (E[]) Arrays.copyOf(heapArray, currentSize);
    }

    private void grow(int desCapacity) {
        heapArray = Arrays.copyOf(heapArray, desCapacity);
    }

    //调用grow()时，必定 maxSize==heapArray.lengh
    private void grow() {
        if (heapArray == DEFAULTCAPACITY_EMPTY_ELEMENTDATA && maxSize == 0) {
            grow(DEFAULT_CAPACITY);
        } else {
            grow(maxSize + (maxSize >>> 1) + 1);
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "heapArray=" + (heapArray == null ? null : Arrays.asList(heapArray)) +
                ", currentSize=" + currentSize +
                ", maxSize=" + maxSize +
                '}';
    }
}
