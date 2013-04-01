package com.psychobit.hardcore;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.endsWith;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Field;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.psychobit.hardcore.Hardcore;
import com.psychobit.hardcore.factories.PlayerFactory;
import com.psychobit.hardcore.factories.PluginManagerFactory;
import com.psychobit.hardcore.factories.ServerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayerLoginEvent.class)
public class HardcoreTest extends DatabaseTest
{
    private HardcoreDAO dao;
    private Hardcore hardcoreListener;

    @Before
    public void before() throws Exception 
    {
        dao = new HardcoreDAO();
        dao.connect(hostname, database, username, password, null);
        
        hardcoreListener = new Hardcore();
        
        Field daoField = Hardcore.class.getDeclaredField("_dao");
        daoField.setAccessible(true);
        daoField.set(hardcoreListener, dao);
    }
    
    @After
    public void after()
    {
        dao.disconnect(null);
    }
    
    
    @Test
    public void testCampfireOnNewPlayerLogin() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
        Player newPlayer = new PlayerFactory().build(); 

        PlayerLoginEvent loginEvent = PowerMockito.mock(PlayerLoginEvent.class);
        when(loginEvent.getPlayer()).thenReturn(newPlayer);
        when(loginEvent.getResult()).thenReturn(Result.ALLOWED);
        
        Server server = new ServerFactory()
            .withPluginManager(new PluginManagerFactory()
                    .withPlugin("Hardcore", hardcoreListener)
                    .build()
            )
            .build();
        
        Field serverfield = JavaPlugin.class.getDeclaredField("server");
        serverfield.setAccessible(true);
        serverfield.set(hardcoreListener, server);        
        
        hardcoreListener.onPlayerLogin(loginEvent);
        
        verify(server).broadcastMessage(endsWith("TestUser is new this month! Say hi!"));
    }
    
    @Test
    public void testCampfireOnExistingPlayerLogin()
    {
    }
}