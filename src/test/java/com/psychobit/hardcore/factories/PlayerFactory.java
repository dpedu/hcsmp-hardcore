package com.psychobit.hardcore.factories;

import static org.mockito.Mockito.when;

import org.bukkit.entity.Player;
import org.powermock.api.mockito.PowerMockito;

public class PlayerFactory
{
    private Player player = PowerMockito.mock(Player.class);
    private String name = "TestUser";

    public PlayerFactory withName(String name)
    {
        this.name = name;
        return this;
    }
    
    public Player build()
    {
        when(player.getName()).thenReturn(name);
        
        return player;
    }
}