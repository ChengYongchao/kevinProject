package com.kevin.collection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /*
     * Vector的分量存储在其中的数组缓冲区。Vector的容量是这个数组缓冲区的长度，并且至少大到可以包含向量的所有元素。
     */
    protected Object[] elementData;

    /*
     * 这个向量对象中有效分量的数量。
     */
    protected int elementCount;

    /*
     * 当向量的大小大于其容量时，其容量自动递增的量。如果容量增量小于或等于零，则每次需要增长时，向量的容量将增加一倍。
     */
    protected int capacityIncrement;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;

    /*
     * 构造具有指定初始容量和容量增量的空Vector。
     */
    public Vector(int initialCapacity, int capacityIncrement)
    {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    public Vector(int initialCapacity)
    {
        this(initialCapacity, 0);
    }

    public Vector()
    {
        this(10);
    }

    /*
     * 构造一个Vector，其中包含指定集合的元素，按集合的迭代器返回元素的顺序排列。
     */
    public Vector(Collection<? extends E> c)
    {
        elementData = c.toArray();
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
    }

    /*
     * 将此Vector的组件复制到指定的数组中。这个Vector中下标k的项被复制到anArray的分量k中。
     */
    public synchronized void copyInto(Object[] anArray)
    {
        System.arraycopy(elementData, 0, anArray, 0, elementCount);
    }

    /*
     * 将该Vector的容量修剪为该Vector的当前大小。如果该Vector的容量大于其当前大小，
     * 则通过将其保存在字段elementData中的内部数据数组替换为一个更小的数组，将容量更改为等于大小。应用程序可以使用此操作最小化向量的存储。
     */
    public synchronized void trimToSize()
    {
        modCount++;
        int oldCapacity = elementData.length;
        if (elementCount < oldCapacity)
        {
            elementData = Arrays.copyOf(elementData, elementCount);
        }
    }

    /*
     * 如果需要，增加这个Vector的容量，以确保它至少可以容纳由最小容量参数指定的组件数量。
     * 如果该Vector的当前容量小于minCapacity，则通过替换字段elementData中的内部数据数组来增加其容量。
     * 新数据数组的大小将是旧的大小加上扩充容量，除非扩充容量的值小于或等于零，在这种情况下，新容量将是旧容量的两倍;
     * 但如果新容量仍然小于minCapacity，那么新容量将是minCapacity。【和ArrayList处理基本相同不多解释】
     */
    public synchronized void ensureCapacity(int minCapacity)
    {
        if (minCapacity > 0)
        {
            modCount++;
            ensureCapacityHelper(minCapacity);
        }
    }

    private void ensureCapacityHelper(int minCapacity)
    {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity)
    {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity)
    {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /*
     * 设置这个Vector的大小。如果新大小大于当前大小，则在向量的末尾添加新的空项。如果新大小小于当前大小，
     * 则索引为newSize之后的所有组件都将被丢弃。
     */
    public synchronized void setSize(int newSize)
    {
        modCount++;
        if (newSize > elementCount)
        {
            ensureCapacityHelper(newSize);
        }
        else
        {
            for (int i = newSize; i < elementCount; i++)
            {
                elementData[i] = null;
            }
        }
        elementCount = newSize;
    }

    /**
     * 返回此向量的当前容量。
     */
    public synchronized int capacity()
    {
        return elementData.length;
    }

    /*
     * 返回这个向量的组件数。
     */
    public synchronized int size()
    {
        return elementCount;
    }

    public synchronized boolean isEmpty()
    {
        return elementCount == 0;
    }

    /*
     * 返回此Vector的组件的枚举。返回的枚举对象将生成此Vector中的所有项。生成的第一个项是索引0处的项，然后是索引1处的项，依此类推。
     */
    public Enumeration<E> elements()
    {
        return new Enumeration<E>()
        {
            int count = 0;

            public boolean hasMoreElements()
            {
                return count < elementCount;
            }

            public E nextElement()
            {
                synchronized (Vector.this)
                {
                    if (count < elementCount)
                    {
                        return elementData(count++);
                    }
                }
                throw new NoSuchElementException("Vector Enumeration");
            }
        };
    }

    /*
     * 如果该向量包含指定的元素，则返回true。
     */
    public boolean contains(Object o)
    {
        return indexOf(o, 0) >= 0;
    }

    /*
     * 返回该向量中指定元素第一次出现的索引，如果该向量不包含该元素，则返回-1。
     */
    public int indexOf(Object o)
    {
        return indexOf(o, 0);
    }

    /*
     * 返回该向量中指定元素第一次出现时的索引，从索引中向前搜索;如果没有找到该元素，则返回-1。
     */
    public synchronized int indexOf(Object o, int index)
    {
        if (o == null)
        {
            for (int i = index; i < elementCount; i++)
                if (elementData[i] == null)
                    return i;
        }
        else
        {
            for (int i = index; i < elementCount; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /*
     * 逆向遍历 返回该向量中指定元素第一次出现的索引，如果该向量不包含该元素，则返回-1。
     */
    public synchronized int lastIndexOf(Object o)
    {
        return lastIndexOf(o, elementCount - 1);
    }

    public synchronized int lastIndexOf(Object o, int index)
    {
        if (index >= elementCount)
            throw new IndexOutOfBoundsException(index + " >= " + elementCount);

        if (o == null)
        {
            for (int i = index; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        }
        else
        {
            for (int i = index; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /*
     * 返回指定索引处的组件。 此方法在功能上与get(int)方法相同(后者是List接口的一部分)。
     */
    public synchronized E elementAt(int index)
    {
        if (index >= elementCount)
        {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }

        return elementData(index);
    }

    /*
     * 返回此向量的第一个组件(索引为0的项)。
     */
    public synchronized E firstElement()
    {
        if (elementCount == 0)
        {
            throw new NoSuchElementException();
        }
        return elementData(0);
    }

    /**
     * Returns the last component of the vector.
     */
    public synchronized E lastElement()
    {
        if (elementCount == 0)
        {
            throw new NoSuchElementException();
        }
        return elementData(elementCount - 1);
    }

    /*
     * 将此向量的指定索引处的组件设置为指定对象。该位置上的前一个组件被丢弃。 索引的值必须大于或等于0，并且小于向量的当前大小。
     * 此方法在功能上与set(int,
     * E)方法(它是List接口的一部分)相同。注意，set方法颠倒了参数的顺序，以便更接近数组的用法。还要注意，set方法返回存储在指定位置的旧值。
     */
    public synchronized void setElementAt(E obj, int index)
    {
        if (index >= elementCount)
        {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }
        elementData[index] = obj;
    }

    /*
     * 删除指定索引处的组件。这个向量中索引大于或等于指定索引的每个分量向下移动，使索引比先前的值小一个。这个向量的大小减少了1。
     * 索引的值必须大于或等于0，并且小于向量的当前大小。
     * 该方法在功能上与remove(int)方法相同(后者是List接口的一部分)。注意，remove方法返回存储在指定位置的旧值。
     */
    public synchronized void removeElementAt(int index)
    {
        modCount++;
        if (index >= elementCount)
        {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }
        /*
         * else if (index < 0) { throw new
         * ArrayIndexOutOfBoundsException(index); }
         */
        int j = elementCount - index - 1;
        if (j > 0)
        {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
    }

    /*
     * 在指定索引处将指定对象作为此Vector中的组件插入。这个Vector中索引大于或等于指定索引的每个分量向上移动，使索引大于先前的值。
     * 索引的值必须大于或等于0，并且小于或等于向量的当前大小。(如果索引等于向量的当前大小，则将新元素追加到Vector。)此方法在功能上与add(
     * int, E)方法(列表接口的一部分)相同。注意，add方法颠倒了参数的顺序，以便更接近于匹配数组的使用。
     * 【这里就很有意思了，对小于elementCount并没有进行判断，以前的方法是判断的了的，即使不判断System.
     * arraycopy也能对负数index抛出正确的异常，因此判断不判断也显得不那么重要了】
     */
    public synchronized void insertElementAt(E obj, int index)
    {
        modCount++;
        if (index > elementCount)
        {
            throw new ArrayIndexOutOfBoundsException(index + " > " + elementCount);
        }
        ensureCapacityHelper(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = obj;
        elementCount++;
    }

    /**
     * Adds the specified component to the end of this vector, increasing its
     * size by one. The capacity of this vector is increased if its size becomes
     * greater than its capacity.
     * <p>
     * This method is identical in functionality to the {@link #add(Object)
     * add(E)} method (which is part of the {@link List} interface).
     *
     * @param obj the component to be added
     */
    public synchronized void addElement(E obj)
    {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = obj;
    }

    /**
     * Removes the first (lowest-indexed) occurrence of the argument from this
     * vector. If the object is found in this vector, each component in the
     * vector with an index greater or equal to the object's index is shifted
     * downward to have an index one smaller than the value it had previously.
     * <p>
     * This method is identical in functionality to the {@link #remove(Object)}
     * method (which is part of the {@link List} interface).
     *
     * @param obj the component to be removed
     * @return {@code true} if the argument was a component of this vector;
     *         {@code false} otherwise.
     */
    public synchronized boolean removeElement(Object obj)
    {
        modCount++;
        int i = indexOf(obj);
        if (i >= 0)
        {
            removeElementAt(i);
            return true;
        }
        return false;
    }

    /**
     * Removes all components from this vector and sets its size to zero.
     * <p>
     * This method is identical in functionality to the {@link #clear} method
     * (which is part of the {@link List} interface).
     */
    public synchronized void removeAllElements()
    {
        modCount++;
        // Let gc do its work
        for (int i = 0; i < elementCount; i++)
            elementData[i] = null;

        elementCount = 0;
    }

    /**
     * Returns a clone of this vector. The copy will contain a reference to a
     * clone of the internal data array, not a reference to the original
     * internal data array of this {@code Vector} object.
     *
     * @return a clone of this vector
     */
    public synchronized Object clone()
    {
        try
        {
            @SuppressWarnings ("unchecked")
            Vector<E> v = (Vector<E>)super.clone();
            v.elementData = Arrays.copyOf(elementData, elementCount);
            v.modCount = 0;
            return v;
        }
        catch (CloneNotSupportedException e)
        {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * Returns an array containing all of the elements in this Vector in the
     * correct order.
     *
     * @since 1.2
     */
    public synchronized Object[] toArray()
    {
        return Arrays.copyOf(elementData, elementCount);
    }

    /**
     * Returns an array containing all of the elements in this Vector in the
     * correct order; the runtime type of the returned array is that of the
     * specified array. If the Vector fits in the specified array, it is
     * returned therein. Otherwise, a new array is allocated with the runtime
     * type of the specified array and the size of this Vector.
     * <p>
     * If the Vector fits in the specified array with room to spare (i.e., the
     * array has more elements than the Vector), the element in the array
     * immediately following the end of the Vector is set to null. (This is
     * useful in determining the length of the Vector <em>only</em> if the
     * caller knows that the Vector does not contain any null elements.)
     *
     * @param a the array into which the elements of the Vector are to be
     *            stored, if it is big enough; otherwise, a new array of the
     *            same runtime type is allocated for this purpose.
     * @return an array containing the elements of the Vector
     * @throws ArrayStoreException if the runtime type of a is not a supertype
     *             of the runtime type of every element in this Vector
     * @throws NullPointerException if the given array is null
     * @since 1.2
     */
    @SuppressWarnings ("unchecked")
    public synchronized <T> T[] toArray(T[] a)
    {
        if (a.length < elementCount)
            return (T[])Arrays.copyOf(elementData, elementCount, a.getClass());

        System.arraycopy(elementData, 0, a, 0, elementCount);

        if (a.length > elementCount)
            a[elementCount] = null;

        return a;
    }

    // Positional Access Operations

    @SuppressWarnings ("unchecked")
    E elementData(int index)
    {
        return (E)elementData[index];
    }

    /**
     * Returns the element at the specified position in this Vector.
     *
     * @param index index of the element to return
     * @return object at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *             ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E get(int index)
    {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this Vector with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *             ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E set(int index, E element)
    {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Appends the specified element to the end of this Vector.
     *
     * @param e element to be appended to this Vector
     * @return {@code true} (as specified by {@link Collection#add})
     * @since 1.2
     */
    public synchronized boolean add(E e)
    {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element in this Vector If
     * the Vector does not contain the element, it is unchanged. More formally,
     * removes the element with the lowest index i such that
     * {@code (o==null ? get(i)==null : o.equals(get(i)))} (if such an element
     * exists).
     *
     * @param o element to be removed from this Vector, if present
     * @return true if the Vector contained the specified element
     * @since 1.2
     */
    public boolean remove(Object o)
    {
        return removeElement(o);
    }

    /**
     * Inserts the specified element at the specified position in this Vector.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *             ({@code index < 0 || index > size()})
     * @since 1.2
     */
    public void add(int index, E element)
    {
        insertElementAt(element, index);
    }

    /**
     * Removes the element at the specified position in this Vector. Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the Vector.
     *
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *             ({@code index < 0 || index >= size()})
     * @param index the index of the element to be removed
     * @return element that was removed
     * @since 1.2
     */
    public synchronized E remove(int index)
    {
        modCount++;
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = elementData(index);

        int numMoved = elementCount - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--elementCount] = null; // Let gc do its work

        return oldValue;
    }

    /**
     * Removes all of the elements from this Vector. The Vector will be empty
     * after this call returns (unless it throws an exception).
     *
     * @since 1.2
     */
    public void clear()
    {
        removeAllElements();
    }

    // Bulk Operations

    /**
     * Returns true if this Vector contains all of the elements in the specified
     * Collection.
     *
     * @param c a collection whose elements will be tested for containment in
     *            this Vector
     * @return true if this Vector contains all of the elements in the specified
     *         collection
     * @throws NullPointerException if the specified collection is null
     */
    public synchronized boolean containsAll(Collection<?> c)
    {
        return super.containsAll(c);
    }

    /**
     * Appends all of the elements in the specified Collection to the end of
     * this Vector, in the order that they are returned by the specified
     * Collection's Iterator. The behavior of this operation is undefined if the
     * specified Collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified Collection is this Vector, and this Vector is nonempty.)
     *
     * @param c elements to be inserted into this Vector
     * @return {@code true} if this Vector changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @since 1.2
     */
    public synchronized boolean addAll(Collection<? extends E> c)
    {
        modCount++;
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(elementCount + numNew);
        System.arraycopy(a, 0, elementData, elementCount, numNew);
        elementCount += numNew;
        return numNew != 0;
    }

    /**
     * Removes from this Vector all of its elements that are contained in the
     * specified Collection.
     *
     * @param c a collection of elements to be removed from the Vector
     * @return true if this Vector changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in this
     *             vector are incompatible with the specified collection
     *             (<a href=
     *             "Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this vector contains one or more null
     *             elements and the specified collection does not support null
     *             elements (<a href=
     *             "Collection.html#optional-restrictions">optional</a>), or if
     *             the specified collection is null
     * @since 1.2
     */
    public synchronized boolean removeAll(Collection<?> c)
    {
        return super.removeAll(c);
    }

    /**
     * Retains only the elements in this Vector that are contained in the
     * specified Collection. In other words, removes from this Vector all of its
     * elements that are not contained in the specified Collection.
     *
     * @param c a collection of elements to be retained in this Vector (all
     *            other elements are removed)
     * @return true if this Vector changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in this
     *             vector are incompatible with the specified collection
     *             (<a href=
     *             "Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this vector contains one or more null
     *             elements and the specified collection does not support null
     *             elements (<a href=
     *             "Collection.html#optional-restrictions">optional</a>), or if
     *             the specified collection is null
     * @since 1.2
     */
    public synchronized boolean retainAll(Collection<?> c)
    {
        return super.retainAll(c);
    }

    /**
     * Inserts all of the elements in the specified Collection into this Vector
     * at the specified position. Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (increases their
     * indices). The new elements will appear in the Vector in the order that
     * they are returned by the specified Collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *            specified collection
     * @param c elements to be inserted into this Vector
     * @return {@code true} if this Vector changed as a result of the call
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *             ({@code index < 0 || index > size()})
     * @throws NullPointerException if the specified collection is null
     * @since 1.2
     */
    public synchronized boolean addAll(int index, Collection<? extends E> c)
    {
        modCount++;
        if (index < 0 || index > elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(elementCount + numNew);

        int numMoved = elementCount - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        elementCount += numNew;
        return numNew != 0;
    }

    /**
     * Compares the specified Object with this Vector for equality. Returns true
     * if and only if the specified Object is also a List, both Lists have the
     * same size, and all corresponding pairs of elements in the two Lists are
     * <em>equal</em>. (Two elements {@code e1} and {@code e2} are
     * <em>equal</em> if {@code (e1==null ? e2==null :
     * e1.equals(e2))}.) In other words, two Lists are defined to be equal if
     * they contain the same elements in the same order.
     *
     * @param o the Object to be compared for equality with this Vector
     * @return true if the specified Object is equal to this Vector
     */
    public synchronized boolean equals(Object o)
    {
        return super.equals(o);
    }

    /**
     * Returns the hash code value for this Vector.
     */
    public synchronized int hashCode()
    {
        return super.hashCode();
    }

    /**
     * Returns a string representation of this Vector, containing the String
     * representation of each element.
     */
    public synchronized String toString()
    {
        return super.toString();
    }

    /**
     * Returns a view of the portion of this List between fromIndex, inclusive,
     * and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned
     * List is empty.) The returned List is backed by this List, so changes in
     * the returned List are reflected in this List, and vice-versa. The
     * returned List supports all of the optional List operations supported by
     * this List.
     * <p>
     * This method eliminates the need for explicit range operations (of the
     * sort that commonly exist for arrays). Any operation that expects a List
     * can be used as a range operation by operating on a subList view instead
     * of a whole List. For example, the following idiom removes a range of
     * elements from a List: <pre>
     *      list.subList(from, to).clear();
     * </pre> Similar idioms may be constructed for indexOf and lastIndexOf, and
     * all of the algorithms in the Collections class can be applied to a
     * subList.
     * <p>
     * The semantics of the List returned by this method become undefined if the
     * backing list (i.e., this List) is <i>structurally modified</i> in any way
     * other than via the returned List. (Structural modifications are those
     * that change the size of the List, or otherwise perturb it in such a
     * fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this List
     * @throws IndexOutOfBoundsException if an endpoint index value is out of
     *             range {@code (fromIndex < 0 || toIndex > size)}
     * @throws IllegalArgumentException if the endpoint indices are out of order
     *             {@code (fromIndex > toIndex)}
     */
    /*
     * public synchronized List<E> subList(int fromIndex, int toIndex) {
     * //return Collections.synchronizedList(super.subList(fromIndex, toIndex),
     * this); }
     */

    /**
     * Removes from this list all of the elements whose index is between
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. Shifts any
     * succeeding elements to the left (reduces their index). This call shortens
     * the list by {@code (toIndex - fromIndex)} elements. (If
     * {@code toIndex==fromIndex}, this operation has no effect.)
     */
    protected synchronized void removeRange(int fromIndex, int toIndex)
    {
        modCount++;
        int numMoved = elementCount - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex, numMoved);

        // Let gc do its work
        int newElementCount = elementCount - (toIndex - fromIndex);
        while (elementCount != newElementCount)
            elementData[--elementCount] = null;
    }

    /**
     * Loads a {@code Vector} instance from a stream (that is, deserializes it).
     * This method performs checks to ensure the consistency of the fields.
     *
     * @param in the stream
     * @throws java.io.IOException if an I/O error occurs
     * @throws ClassNotFoundException if the stream contains data of a
     *             non-existing class
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        ObjectInputStream.GetField gfields = in.readFields();
        int count = gfields.get("elementCount", 0);
        Object[] data = (Object[])gfields.get("elementData", null);
        if (count < 0 || data == null || count > data.length)
        {
            throw new StreamCorruptedException("Inconsistent vector internals");
        }
        elementCount = count;
        elementData = data.clone();
    }

    /**
     * Save the state of the {@code Vector} instance to a stream (that is,
     * serialize it). This method performs synchronization to ensure the
     * consistency of the serialized data.
     */
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException
    {
        final java.io.ObjectOutputStream.PutField fields = s.putFields();
        final Object[] data;
        synchronized (this)
        {
            fields.put("capacityIncrement", capacityIncrement);
            fields.put("elementCount", elementCount);
            data = elementData.clone();
        }
        fields.put("elementData", data);
        s.writeFields();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list. The specified
     * index indicates the first element that would be returned by an initial
     * call to {@link ListIterator#next next}. An initial call to
     * {@link ListIterator#previous previous} would return the element with the
     * specified index minus one.
     * <p>
     * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public synchronized ListIterator<E> listIterator(int index)
    {
        if (index < 0 || index > elementCount)
            throw new IndexOutOfBoundsException("Index: " + index);
        return new ListItr(index);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     * <p>
     * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @see #listIterator(int)
     */
    public synchronized ListIterator<E> listIterator()
    {
        return new ListItr(0);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * <p>
     * The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public synchronized Iterator<E> iterator()
    {
        return new Itr();
    }

    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements Iterator<E>
    {
        int cursor; // index of next element to return

        int lastRet = -1; // index of last element returned; -1 if no such

        int expectedModCount = modCount;

        public boolean hasNext()
        {
            // Racy but within spec, since modifications are checked
            // within or after synchronization in next/previous
            return cursor != elementCount;
        }

        public E next()
        {
            synchronized (Vector.this)
            {
                checkForComodification();
                int i = cursor;
                if (i >= elementCount)
                    throw new NoSuchElementException();
                cursor = i + 1;
                return elementData(lastRet = i);
            }
        }

        public void remove()
        {
            if (lastRet == -1)
                throw new IllegalStateException();
            synchronized (Vector.this)
            {
                checkForComodification();
                Vector.this.remove(lastRet);
                expectedModCount = modCount;
            }
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action)
        {
            Objects.requireNonNull(action);
            synchronized (Vector.this)
            {
                final int size = elementCount;
                int i = cursor;
                if (i >= size)
                {
                    return;
                }
                @SuppressWarnings ("unchecked")
                final E[] elementData = (E[])Vector.this.elementData;
                if (i >= elementData.length)
                {
                    throw new ConcurrentModificationException();
                }
                while (i != size && modCount == expectedModCount)
                {
                    action.accept(elementData[i++]);
                }
                // update once at end of iteration to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification()
        {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * An optimized version of AbstractList.ListItr
     */
    final class ListItr extends Itr implements ListIterator<E>
    {
        ListItr(int index)
        {
            super();
            cursor = index;
        }

        public boolean hasPrevious()
        {
            return cursor != 0;
        }

        public int nextIndex()
        {
            return cursor;
        }

        public int previousIndex()
        {
            return cursor - 1;
        }

        public E previous()
        {
            synchronized (Vector.this)
            {
                checkForComodification();
                int i = cursor - 1;
                if (i < 0)
                    throw new NoSuchElementException();
                cursor = i;
                return elementData(lastRet = i);
            }
        }

        public void set(E e)
        {
            if (lastRet == -1)
                throw new IllegalStateException();
            synchronized (Vector.this)
            {
                checkForComodification();
                Vector.this.set(lastRet, e);
            }
        }

        public void add(E e)
        {
            int i = cursor;
            synchronized (Vector.this)
            {
                checkForComodification();
                Vector.this.add(i, e);
                expectedModCount = modCount;
            }
            cursor = i + 1;
            lastRet = -1;
        }
    }

    @Override
    public synchronized void forEach(Consumer<? super E> action)
    {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings ("unchecked")
        final E[] elementData = (E[])this.elementData;
        final int elementCount = this.elementCount;
        for (int i = 0; modCount == expectedModCount && i < elementCount; i++)
        {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    @SuppressWarnings ("unchecked")
    public synchronized boolean removeIf(Predicate<? super E> filter)
    {
        Objects.requireNonNull(filter);
        // figure out which elements are to be removed
        // any exception thrown from the filter predicate at this stage
        // will leave the collection unmodified
        int removeCount = 0;
        final int size = elementCount;
        final BitSet removeSet = new BitSet(size);
        final int expectedModCount = modCount;
        for (int i = 0; modCount == expectedModCount && i < size; i++)
        {
            @SuppressWarnings ("unchecked")
            final E element = (E)elementData[i];
            if (filter.test(element))
            {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }

        // shift surviving elements left over the spaces left by removed
        // elements
        final boolean anyToRemove = removeCount > 0;
        if (anyToRemove)
        {
            final int newSize = size - removeCount;
            for (int i = 0, j = 0; (i < size) && (j < newSize); i++, j++)
            {
                i = removeSet.nextClearBit(i);
                elementData[j] = elementData[i];
            }
            for (int k = newSize; k < size; k++)
            {
                elementData[k] = null; // Let gc do its work
            }
            elementCount = newSize;
            if (modCount != expectedModCount)
            {
                throw new ConcurrentModificationException();
            }
            modCount++;
        }

        return anyToRemove;
    }

    @Override
    @SuppressWarnings ("unchecked")
    public synchronized void replaceAll(UnaryOperator<E> operator)
    {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = elementCount;
        for (int i = 0; modCount == expectedModCount && i < size; i++)
        {
            elementData[i] = operator.apply((E)elementData[i]);
        }
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public synchronized void sort(Comparator<? super E> c)
    {
        final int expectedModCount = modCount;
        Arrays.sort((E[])elementData, 0, elementCount, c);
        if (modCount != expectedModCount)
        {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    /**
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * list.
     * <p>
     * The {@code Spliterator} reports {@link Spliterator#SIZED},
     * {@link Spliterator#SUBSIZED}, and {@link Spliterator#ORDERED}. Overriding
     * implementations should document the reporting of additional
     * characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @since 1.8
     */
    @Override
    public Spliterator<E> spliterator()
    {
        return new VectorSpliterator<>(this, null, 0, -1, 0);
    }

    /** Similar to ArrayList Spliterator */
    static final class VectorSpliterator<E> implements Spliterator<E>
    {
        private final Vector<E> list;

        private Object[] array;

        private int index; // current index, modified on advance/split

        private int fence; // -1 until used; then one past last index

        private int expectedModCount; // initialized when fence set

        /** Create new spliterator covering the given range */
        VectorSpliterator(Vector<E> list, Object[] array, int origin, int fence, int expectedModCount)
        {
            this.list = list;
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence()
        { // initialize on first use
            int hi;
            if ((hi = fence) < 0)
            {
                synchronized (list)
                {
                    array = list.elementData;
                    expectedModCount = list.modCount;
                    hi = fence = list.elementCount;
                }
            }
            return hi;
        }

        public Spliterator<E> trySplit()
        {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : new VectorSpliterator<E>(list, array, lo, index = mid, expectedModCount);
        }

        @SuppressWarnings ("unchecked")
        public boolean tryAdvance(Consumer<? super E> action)
        {
            int i;
            if (action == null)
                throw new NullPointerException();
            if (getFence() > (i = index))
            {
                index = i + 1;
                action.accept((E)array[i]);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                return true;
            }
            return false;
        }

        @SuppressWarnings ("unchecked")
        public void forEachRemaining(Consumer<? super E> action)
        {
            int i, hi; // hoist accesses and checks from loop
            Vector<E> lst;
            Object[] a;
            if (action == null)
                throw new NullPointerException();
            if ((lst = list) != null)
            {
                if ((hi = fence) < 0)
                {
                    synchronized (lst)
                    {
                        expectedModCount = lst.modCount;
                        a = array = lst.elementData;
                        hi = fence = lst.elementCount;
                    }
                }
                else
                    a = array;
                if (a != null && (i = index) >= 0 && (index = hi) <= a.length)
                {
                    while (i < hi)
                        action.accept((E)a[i++]);
                    if (lst.modCount == expectedModCount)
                        return;
                }
            }
            throw new ConcurrentModificationException();
        }

        public long estimateSize()
        {
            return (long)(getFence() - index);
        }

        public int characteristics()
        {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }
}
