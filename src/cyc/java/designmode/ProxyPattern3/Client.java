package cyc.java.designmode.ProxyPattern3;

public class Client
{
    public static void main(String[] args)
    {
        GamePlayer player = new GamePlayer("二狗");
        GamePlayerProxy proxy = new GamePlayerProxy(player);
        proxy.login("123","123");
        proxy.killBoss();
        proxy.upgrade();
    }
}
