package com.psychobit.hardcore.factories;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.powermock.api.mockito.PowerMockito;

public class ServerFactory
{
    private Server server = PowerMockito.mock(Server.class);
    private String name = "TestBukkit";
    
    private PluginManager pluginManager = new PluginManagerFactory().build();
    private BukkitScheduler scheduler = new BukkitSchedulerFactory().build();
    private ArrayList<Player> players = new ArrayList<Player>(); 
    
    public ServerFactory withPluginManager(PluginManager pluginManager)
    {
        this.pluginManager = pluginManager;
        return this;
    }
    
    public ServerFactory withPlayer(Player player)
    {
        when(server.getPlayer(player.getName())).thenReturn(player);
        return this;
    }
    
    public Server build()
    {
        when(server.getName()).thenReturn(name);
        when(server.getLogger()).thenReturn(Logger.getLogger("Minecraft"));
        when(server.getPluginManager()).thenReturn(pluginManager);
        when(server.getOnlinePlayers()).thenReturn( players.toArray(new Player[0]) );
        when(server.getScheduler()).thenReturn(scheduler);
        
        // when(mockServer.getWorldContainer()).thenReturn(worldsDirectory);
        
        
        return server;
    }
}