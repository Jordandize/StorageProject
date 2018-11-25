package ua.edu.ukma.gpd.storage.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.TabsDao;
import ua.edu.ukma.gpd.storage.entity.TabSidebar;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TabsDaoImpl implements TabsDao {

   

	@Override
	public List<TabSidebar> findUserTabs() {
		   List<TabSidebar> tabs= new ArrayList();
		   tabs.add(new TabSidebar(1,"CreateOrder","/order"));
		   tabs.add(new TabSidebar(2,"List of Orders","/list"));
		   tabs.add(new TabSidebar(3,"List of Products","/products"));
	        return tabs;
	 }

    @Override
    public List<TabSidebar> findAdminTabs() {
    	 List<TabSidebar> tabs= new ArrayList();
		   tabs.add(new TabSidebar(1,"CreateOrder","/order"));
		   tabs.add(new TabSidebar(2,"List of Orders","/list"));
		   tabs.add(new TabSidebar(3,"List of Products","/products"));
		   tabs.add(new TabSidebar(4,"Something1","/something"));
		   tabs.add(new TabSidebar(5,"Something2","/something2"));
	        return tabs;
    }

    @Override
    public List<TabSidebar> findKeeperTabs() {
    	 List<TabSidebar> tabs= new ArrayList();
		   tabs.add(new TabSidebar(1,"CreateOrder","/order"));
		   tabs.add(new TabSidebar(2,"List of Orders","/list"));
		   tabs.add(new TabSidebar(3,"List of Products","/products"));
		   tabs.add(new TabSidebar(4,"Something1","/something"));
		  
	        return tabs;
    }


}
