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
		   tabs.add(new TabSidebar("Products","products", "ballot"));
		   tabs.add(new TabSidebar("Create Order","create-order", "queue"));
		   tabs.add(new TabSidebar("Orders","orders", "sort"));
	        return tabs;
	 }

    @Override
    public List<TabSidebar> findAdminTabs() {
    	 List<TabSidebar> tabs= new ArrayList();
		   tabs.add(new TabSidebar("Order List","adminOrders", "book"));
		   tabs.add(new TabSidebar("Create Products","productsOperations", "view_module"));
		   tabs.add(new TabSidebar("Categories","categories", "line_weight"));
		   tabs.add(new TabSidebar("Users roles","user-management", "assignment_ind"));
	        return tabs;
    }

    @Override
    public List<TabSidebar> findKeeperTabs() {
    	 List<TabSidebar> tabs= new ArrayList();
		   tabs.add(new TabSidebar("Queue","queue", "compare_arrows"));
	        return tabs;
    }


}
