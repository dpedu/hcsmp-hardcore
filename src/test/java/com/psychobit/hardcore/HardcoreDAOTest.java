package com.psychobit.hardcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.internal.runners.JUnit4ClassRunner;

import com.psychobit.hardcore.HardcoreDAO;

@RunWith(JUnit4ClassRunner.class)
public class HardcoreDAOTest extends DatabaseTest
{
    HardcoreDAO dao;
    
    @Before
    public void before() 
    {
        dao = new HardcoreDAO();
        dao.connect(hostname, database, username, password, null);
    }
    
    @After
    public void after() throws SQLException
    {
        dao.disconnect(null);
    }
    
    @Test
    public void testSurvivalTimeForNonExistantPlayer()
    {
        int survivalTime = dao.survivalTime("DoesNotExist", false);
        Assert.assertEquals("should return -1 survival time for non existant player", -1, survivalTime);
    }
    
    @Test
    public void testSurvivalTimeForOnlinePlayer() throws SQLException, InterruptedException
    {
        PreparedStatement s = dbConnection.prepareStatement(
                "INSERT INTO `survival` ( `Username`, `Joined`, `LastOnline`, `SurvivalTime` ) VALUES( 'OnlinePlayer', NOW(), NOW(), 1)" 
        );
        s.executeUpdate();
        
        Thread.sleep(1000);
        
        int survivalTime = dao.survivalTime("OnlinePlayer", true);
        Assert.assertTrue("should return 2 to 3 seconds for online player survial time", 2 <= survivalTime && 3 >= survivalTime);
    }
    
    @Test
    public void testSurvivalTimeForOfflinePlayer() throws SQLException, InterruptedException
    {
        PreparedStatement s = dbConnection.prepareStatement(
                "INSERT INTO `survival` ( `Username`, `Joined`, `LastOnline`, `SurvivalTime` ) VALUES( 'OfflinePlayer', NOW(), NOW(), 1)" 
        );
        s.executeUpdate();
        
        Thread.sleep(1000);
        
        int survivalTime = dao.survivalTime("OfflinePlayer", false);
        Assert.assertEquals("should return 1 second for offline player survial time", 1, survivalTime);
        
        Thread.sleep(1000);
        
        survivalTime = dao.survivalTime("OfflinePlayer", false);
        Assert.assertEquals("should not increase survival time while player is offline", 1, survivalTime);        
    }
    
    protected int selectScalarInteger(String sql) throws SQLException
    {
        PreparedStatement s = dbConnection.prepareStatement(sql);
        
        ResultSet rs = s.executeQuery();
        
        return rs.getInt(1);
    }
}