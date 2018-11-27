package ua.edu.ukma.gpd.storage.entity;

public class TabSidebar {


	    private int id;

	    private String name;

	    private String url;




	    public TabSidebar(int id, String name, String url) {
			this.id=id;
			this.name=name;
			this.url=url;
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
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


	    @Override
	    public String toString() {
	        return "Tab{" +
	                "tabId=" + id +
	                ", name=" + name +
	                ", url=" + url +
	                '}';
	    }
	}

