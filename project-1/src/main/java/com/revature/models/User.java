package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid")
	private int userid;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="firstname", nullable=false)
	private String firstname;
	
	@Column(name="lastname", nullable=false)
	private String lastname;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="roleid", nullable=false)
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy="author")
	private List<Reimbursement> authoredReimbursments;
	
	@JsonIgnore
	@OneToMany(mappedBy="resolver")
	private List<Reimbursement> resolvedReimbursments;
	
	public User() {}
	public User(int userid, String username, String password, String firstname, 
			String lastname, String email, Role role, List<Reimbursement> authoredReimbursments,
			List<Reimbursement> resolvedReimbursments) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.authoredReimbursments = authoredReimbursments;
		this.resolvedReimbursments = resolvedReimbursments;
	}
	public User(String username, String password, String firstname, 
			String lastname, String email, Role role, List<Reimbursement> authoredReimbursments,
			List<Reimbursement> resolvedReimbursments) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.authoredReimbursments = authoredReimbursments;
		this.resolvedReimbursments = resolvedReimbursments;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Reimbursement> getAuthoredReimbursments() {
		return authoredReimbursments;
	}
	public void setAuthoredReimbursments(List<Reimbursement> authoredReimbursments) {
		this.authoredReimbursments = authoredReimbursments;
	}
	public List<Reimbursement> getResolvedReimbursments() {
		return resolvedReimbursments;
	}
	public void setResolvedReimbursments(List<Reimbursement> resolvedReimbursments) {
		this.resolvedReimbursments = resolvedReimbursments;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", role=" + role.getRole()
				+ "]";
	}
}
