package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reimbursment_type")
public class Type {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="typeid")
	private int typeid;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@JsonIgnore
	@OneToMany(mappedBy="type")
	private List<Reimbursement> reimbursments;
	
	public Type() {}
	public Type(int typeid, String type, List<Reimbursement> reimbursments) {
		super();
		this.typeid = typeid;
		this.type = type;
		this.reimbursments = reimbursments;
	}
	public Type(String type, List<Reimbursement> reimbursments) {
		super();
		this.type = type;
		this.reimbursments = reimbursments;
	}
	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Reimbursement> getReimbursments() {
		return reimbursments;
	}
	public void setReimbursments(List<Reimbursement> reimbursments) {
		this.reimbursments = reimbursments;
	}
	
	@Override
	public String toString() {
		return "Type [typeid=" + typeid + ", type=" + type + "]";
	}
}
