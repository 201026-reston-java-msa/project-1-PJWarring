package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid")
	private int roleid;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<User> users;
	
	public Role() {}
	public Role(int roleid, String role) {
		this.roleid = roleid;
		this.role = role;
	}
	public Role(String role) {
		this.role = role;
	}
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getUsers() {
		return users;
	}
	
	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", role=" + role + "]";
	}
}
