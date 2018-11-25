package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.TabSidebar;

public interface TabsService {

    List<TabSidebar> findUserTabs() throws Exception;

    List<TabSidebar> findAdminTabs() throws Exception;
    
    List<TabSidebar> findKeeperTabs() throws Exception;
}
