
package com.kevin.leetcode;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年11月6日
 */
public class Solution002
{

    @Test
    public void test()
    {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        addTwoNumbers(l1, l2);
    }

    @Test
    public void test2()
    {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        addTwoNumbers(l1, l2);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode res = new ListNode(0);
        ListNode result = res;
        while (l1 != null || l2 != null)
        {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = res.val + v1 + v2;         
            if (sum >= 10)
            {
                res.val = sum - 10;
                res.next = new ListNode(1);
            }
            else
            {
                res.val = sum;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if((l1!=null || l2 != null) && res.next == null) res.next = new ListNode(0);
            res = res.next;
        }
        return result;
    }

    public class ListNode
    {
        int val;

        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }
}
