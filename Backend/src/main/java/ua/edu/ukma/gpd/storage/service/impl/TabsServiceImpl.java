package ua.edu.ukma.gpd.storage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.TabsDao;
import ua.edu.ukma.gpd.storage.entity.TabSidebar;
import ua.edu.ukma.gpd.storage.service.TabsService;

import java.util.List;

@Service
public class TabsServiceImpl implements TabsService {

    @Autowired
    private TabsDao tabsDao;

   

    @Override
    public List<TabSidebar> findUserTabs() throws Exception{
        List<TabSidebar> tabs = null;
        try{
            tabs = tabsDao.findUserTabs();
        } catch (EmptyResultDataAccessException e){

        } catch (Exception e){
            throw new Exception("Failed to find userTabs");
        }
        return tabs;
    }
    @Override
    public List<TabSidebar> findAdminTabs() throws Exception{
        List<TabSidebar> tabs = null;
        try{
            tabs = tabsDao.findAdminTabs();
        } catch (EmptyResultDataAccessException e){

        } catch (Exception e){
            throw new Exception("Failed to find adminTabs");
        }
        return tabs;
    }
    @Override
    public List<TabSidebar> findKeeperTabs() throws Exception{
        List<TabSidebar> tabs = null;
        try{
            tabs = tabsDao.findKeeperTabs();
        } catch (EmptyResultDataAccessException e){

        } catch (Exception e){
            throw new Exception("Failed to find keeperTabs");
        }
        return tabs;
    }
    

   
}
