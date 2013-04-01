package com.psychobit.hardcore.factories;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.powermock.api.mockito.PowerMockito;

public class PluginManagerFactory
{
    PluginManager pluginManager = PowerMockito.mock(PluginManager.class);
    ArrayList<JavaPlugin> plugins = new ArrayList<JavaPlugin>();
    
    public PluginManagerFactory withPlugin(String pluginName, JavaPlugin plugin)
    {
        when(pluginManager.getPlugin(pluginName)).thenReturn(plugin);
        return this;
    }
    
    public PluginManager build()
    {
        when(pluginManager.getPlugins()).thenReturn( plugins.toArray(new JavaPlugin[0]) );
        when(pluginManager.getPermission(anyString())).thenReturn(null);
        
        return pluginManager;
    }
}
