package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.TabSidebar;
import ua.edu.ukma.gpd.storage.service.TabsService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
      
        List<TabSidebar> tabs = null;
        String admin="ADMIN";
        String user="USER";
        String keeper="KEEPER";
        try{
        	
        	if(roles.contains(user)) {
            tabs = tabsService.findUserTabs();
        	}
            if(roles.contains(keeper)) {
                tabs = tabsService.findKeeperTabs();
            	}
           if(roles.contains(admin)) {
                tabs = tabsService.findAdminTabs();
            	}
        } catch (Exception  e){
            e.printStackTrace();
            tabs = null;
        }
        return tabs;
    }

}
