package com.gkalal.apidemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

class GetUserResponse {
	@SerializedName("page") @Expose private int page;
	@SerializedName("per_page") @Expose private int perPage;
	@SerializedName("total") @Expose private int total;
	@SerializedName("total_pages") @Expose private int totalPages;
	@SerializedName("data") @Expose private List<Data> data = null;
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPerPage() {
		return perPage;
	}
	
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public List<Data> getData() {
		return data;
	}
	
	public void setData(List<Data> data) {
		this.data = data;
	}
	
	public class Data {
		
		@SerializedName("id") @Expose private int id;
		@SerializedName("first_name") @Expose private String firstName;
		@SerializedName("last_name") @Expose private String lastName;
		@SerializedName("avatar") @Expose private String avatar;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getAvatar() {
			return avatar;
		}
		
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
	}
}
