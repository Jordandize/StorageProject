package ua.edu.ukma.gpd.storage.dao;
import ua.edu.ukma.gpd.storage.entity.TabSidebar;

import java.util.List;
public interface TabsDao {

	    List<TabSidebar> findUserTabs();
	    
	    List<TabSidebar> findAdminTabs();
	    
	    List<TabSidebar> findKeeperTabs();

}
