
package cyc.mengtu.gdmGraphToNeo4j;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年10月14日
 */
class Vertex
{
    public String V = null;

    public String neo4jV = null;

    Vertex(String id)
    {

        this.V = getId(id);
        this.neo4jV = "(a" + V + ":User {id:'" + V + "',label:" + V + "})";
    }

    private String getId(String id)
    {
        return id.replaceAll("v", "").replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
