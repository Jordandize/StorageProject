package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.entity.TabSidebar;
import ua.edu.ukma.gpd.storage.service.TabsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/tabs")
public class TabsController {

    @Autowired
    private TabsService tabsService;

    @GetMapping
    public List<TabSidebar> getUserTabs() throws Exception{
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> role= authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : role) {
            roles.add(a.getAuthority());
        }
      
  
        String admin="ROLE_ADMIN";
        String user="ROLE_USER";
        String keeper="ROLE_KEEPER";
        List<TabSidebar> list = new ArrayList<>();
        try{
        	
        	if(roles.contains(user)) {
        	List<TabSidebar> tabs = tabsService.findUserTabs();
            list.addAll(tabs);
        	}
            if(roles.contains(keeper)) {
            	List<TabSidebar> tabs = tabsService.findKeeperTabs();
            	 list.addAll(tabs);
            	}
           if(roles.contains(admin)) {
        	   List<TabSidebar>  tabs = tabsService.findAdminTabs();
        	   list.addAll(tabs);
            	}
        } catch (Exception  e){
            e.printStackTrace();
            list = null;
        }
        return list;
    }

}
