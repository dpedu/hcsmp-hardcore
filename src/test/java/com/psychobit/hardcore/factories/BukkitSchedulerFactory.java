package com.psychobit.hardcore.factories;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

public class BukkitSchedulerFactory
{
    private BukkitScheduler mockScheduler = PowerMockito.mock(BukkitScheduler.class);
    
    public BukkitScheduler build()
    {
        when(mockScheduler.scheduleSyncDelayedTask(any(Plugin.class), any(Runnable.class), anyLong())).
        thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Runnable arg;
                try {
                    arg = (Runnable) invocation.getArguments()[1];
                } catch (Exception e) {
                    return null;
                }
                arg.run();
                return null;
            }});
        when(mockScheduler.scheduleSyncDelayedTask(any(Plugin.class), any(Runnable.class))).
        thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Runnable arg;
                try {
                    arg = (Runnable) invocation.getArguments()[1];
                } catch (Exception e) {
                    return null;
                }
                arg.run();
                return null;
            }});        
        return mockScheduler;
    }
}