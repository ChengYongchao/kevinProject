package cyc.java.test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月24日
 */
public class Test
{
    public enum Status
    {
        NOJOB, INITIALIZED, COMPUTING;
    }

    public static void main(String[] args)
    {
        System.out.println(Status.NOJOB.toString());

        Algorithm a1 = Algorithm.PAGE_RANK;
        Algorithm a2 = Algorithm.PAGE_RANK;
        System.out.println(a1 == a2);
        System.out.println(a1 == Algorithm.PAGE_RANK);

    }

    public enum Algorithm
    {
        UN_KNOWN(-1), PAGE_RANK(0), SHORTEST_PATH(1);

        // int型变量，标识算法类型
        // 这里考虑到存入数据库时不把枚举进去
        // 所以用个int来标记对应算法类型
        private int value;

        Algorithm(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }

        /**
         * 根据int类型值获取对应的枚举算法类型
         * 
         * @param value int类型的算法类型
         * @return 与value相对应的{@code Algorithm}
         */
        public Algorithm getAlgorithmByValue(int value)
        {
            switch (value)
            {
                case 0:
                    return PAGE_RANK;
                case 1:
                    return SHORTEST_PATH;
                default:
                    return UN_KNOWN;
            }
        }
    }
}
