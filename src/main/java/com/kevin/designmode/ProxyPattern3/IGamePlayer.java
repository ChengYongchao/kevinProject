package com.kevin.designmode.ProxyPattern3;

public interface IGamePlayer
{
    public void login(String name, String Password);

    public void killBoss();

    public void upgrade();

    public GamePlayerProxy getProxy();
}
