package com.balionis.spring3.model;

import java.io.Serializable;

public class AppModelID implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long key;
	
    public AppModelID(Long key) {
        this.key = key;
    }
    public AppModelID(AppModelID obj) {
        this.key = obj.key;
    }
    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		AppModelID other = (AppModelID) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "AppModelID [key=" + key + "]";
	}	
}
