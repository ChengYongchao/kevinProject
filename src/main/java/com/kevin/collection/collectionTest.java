package com.kevin.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class collectionTest
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(4);
        list2.add(4);
        list2.add(4);
        // list1.addAll(1, list2);
        // list1.addAll(2, list2);
        // Integer[] arr1 = {8, 8};
        // Integer[] arr2 = list1.toArray(arr1);
        // System.out.println(arr2.toString());
        ArrayList<Integer> list3 = (ArrayList<Integer>)list1.clone();
        list3.addAll(list3);
        Iterator<Integer> it = list3.listIterator(1);
        System.out.println(it.next());
        System.out.println(list3.toString());

        /**** Vector- ******/
        Vector<Integer> v1 = new Vector<>();
        v1.add(1);
        v1.add(2);
        v1.add(3);

        Vector<Integer> v2 = new Vector<>();
        v2.add(1);
        v2.add(2);
        v2.add(3);
        v2.add(4);
        ArrayList<Integer> v3 = new ArrayList<>();
        v3.add(1);

        Object[] v4 = v1.toArray(v3.toArray());
        System.out.println(v4.toString());
        /*
         * v1.removeElementAt(-1); v1.insertElementAt(4, -1);
         */
        System.out.println(v1.toArray().toString());

        linkedListTest();
    }

    public static void linkedListTest()
    {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(1);
        l1.add(2);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(4);
        list1.add(5);
        l1.addAll(list1);
    }

    @Test
    public void testListToArray()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Integer[] arr1 = list.toArray(new Integer[list.size()]);
    }

    @Test
    public void testIterator()
    {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 2);
        map.put(3, 2);
        map.put(4, 2);
        map.put(5, 2);
        map.put(6, 2);
        map.put(7, 2);
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        Iterator<Integer> it = arr.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
            it.remove();
        }

    }
}
