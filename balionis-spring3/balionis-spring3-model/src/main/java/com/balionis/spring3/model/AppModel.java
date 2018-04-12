package com.balionis.spring3.model;

import java.util.Date;

public class AppModel {

	private AppModelID id;
	
	private String name;

	private String type;
	
    private Date modifyAt;
    
	public AppModel(AppModelID id) {
        this.id = id;
    }
	public AppModel(AppModelID id, AppModel obj) {
        this.id = id;
        this.name = obj.name;
        this.modifyAt = obj.modifyAt;
        this.type = obj.type;
    }
    public AppModelID getId() {
        return id;
    }
    public void setId(AppModelID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }    
    public Date getModifyAt() {
        return modifyAt;
    }
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifyAt == null) ? 0 : modifyAt.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AppModel other = (AppModel) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) { 
			return false;
		}
		if (modifyAt == null) {
			if (other.modifyAt != null) {
				return false;
			}
		} else if (!modifyAt.equals(other.modifyAt)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "AppModel [" 
	            + "id=" + id + ", name=" + name + ", modifyAt=" + modifyAt 
				+ ", type="	+ type + "]";
	}  
	
}
