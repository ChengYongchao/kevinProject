
package com.kevin.mengtu.gdmGraphToNeo4j;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年10月14日
 */
class Edge
{
    public String v1 = null;

    public String v2 = null;

    public String neo4jE = null;

    Edge(String e)
    {
        getV(e);
        this.neo4jE = "(a" + v1 + ")-[:FOLLOW]->(a" + v2 + ")";
    }

    /**
     * 
     */
    private void getV(String e)
    {
        int index = e.lastIndexOf('[');
        String vv = e.substring(index + 1, e.length() -1);
        String [] vs = vv.split("->");
        this.v1 = vs[0];
        this.v2 = vs[1];
    }

}
