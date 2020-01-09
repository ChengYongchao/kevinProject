package cyc.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution003
{
    public static int solution(String s)
    {
        if (s == null || s.length() == 0)
            return 0;
        int maxLength = 0;
        int length = 0;
        Map<Character, Integer> c = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++)
        {
            if (c.containsKey(s.charAt(i)))
            {
                length = Math.max(length, c.get(s.charAt(i)));
            }
            maxLength = Math.max(maxLength, i + 1 - length);
            c.put(s.charAt(i), i + 1);
        }
        return maxLength;
    }

    public static void main(String[] args)
    {
        System.out.println(solution(" "));
    }
}
