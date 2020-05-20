package com.kevin.designmode.ProxyPattern1;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月23日
 */
public class GamePlayer implements IGamePlayer
{
    private String name;

    public GamePlayer(String name)
    {
        this.name = name;
    }

    @Override
    public void login(String userNumber, String Password)
    {
        System.out.println("登录名为" + userNumber + "的用户" + this.name + "登录成功！");
    }

    @Override
    public void killBoss()
    {
        System.out.println(name + "在打怪");
    }

    @Override
    public void upgrade()
    {
        System.out.println(name + "升级了");
    }

}
