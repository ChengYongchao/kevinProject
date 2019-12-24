package cyc.java.designmode.ProxyPattern_DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client
{
    public static void main(String[] args)
    {
        IGamePlayer player = new GamePlayer("pikapika");
        InvocationHandler handler = new GamePlayIH(player);
        ClassLoader cl = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer)Proxy.newProxyInstance(cl, new Class[] {IGamePlayer.class}, handler);
        proxy.login("123", "123");
        proxy.killBoss();
        proxy.upgrade();
    }
}
