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
@Table(name="reimbursment_status")
public class Status {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="statusid")
	private int statusid;
	
	@Column(name="status", nullable=false)
	private String status;
	
	@JsonIgnore
	@OneToMany(mappedBy="status")
	private List<Reimbursement> reimbursements;
	
	public Status() {}
	public Status(int statusid, String status, List<Reimbursement> reimbursements) {
		this.statusid = statusid;
		this.status = status;
		this.reimbursements = reimbursements;
	}
	public Status(String status, List<Reimbursement> reimbursements) {
		this.status = status;
		this.reimbursements = reimbursements;
	}
	
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}
	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	
	@Override
	public String toString() {
		return "Status [statusid=" + statusid + ", status=" + status + "]";
	}
}
