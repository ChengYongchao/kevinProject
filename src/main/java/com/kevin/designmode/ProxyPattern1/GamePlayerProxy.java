package com.kevin.designmode.ProxyPattern1;

public class GamePlayerProxy implements IGamePlayer
{
    private IGamePlayer gamePlayer;

    public GamePlayerProxy(IGamePlayer player)
    {
        this.gamePlayer = player;
    }

    @Override
    public void login(String name, String Password)
    {
        this.gamePlayer.login(name, Password);
    }

    @Override
    public void killBoss()
    {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade()
    {
        this.gamePlayer.upgrade();
    }

}
