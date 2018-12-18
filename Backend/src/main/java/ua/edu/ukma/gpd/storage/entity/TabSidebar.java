package ua.edu.ukma.gpd.storage.entity;

public class TabSidebar {


	  

	    private String name;

	    private String url;

	    private String  icon;


	    public TabSidebar( String name, String url,String icon) {
			
			this.name=name;
			this.url=url;
			this.icon=icon;
		}

		
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
	    public String getIcon() {
	        return icon;
	    }

	    public void setIcon(String icon) {
	        this.icon = icon;
	    }
	    
	    @Override
	    public boolean equals(Object ts) {
	    	if(ts == null) return false;
	    	if(ts instanceof TabSidebar) {
	    		return name.equals(((TabSidebar) ts).name);
	    	}
	    	return false;
	    }


	    @Override
	    public String toString() {
	        return "Tab{" +
	                "  name=" + name +
	                ", url=" + url +
	                ", icon=" + icon +
	                '}';
	    }
	}

