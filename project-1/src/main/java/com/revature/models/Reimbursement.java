package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimbursments")
public class Reimbursement {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimbursmentid")
	private int reimbursmentid;
	
	@Column(name="amount", nullable=false)
	private double amount;
	
	@Column(name="submitted", nullable=false)
	private Timestamp submitted;
	
	@Column(name="resolved")
	private Timestamp resolved;
	
	@Column(name="description")
	private String description;
	
	/*@Column(name="reciept")
	private ??? recipt; //TODO: add getter/setter, add to constructors, update toString */
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="author")
	private User author;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="resolver")
	private User resolver;

	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="statusid", nullable=false)
	private Status status;

	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="typeid", nullable=false)
	private Type type;
	
	public Reimbursement() {}
	public Reimbursement(int reimbursmentid, double amount, Timestamp submitted, Timestamp resolved,
			String description, User author, User resolver, Status status, Type type) {
		this.reimbursmentid = reimbursmentid;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description, User author,
			User resolver, Status status, Type type) {
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	public int getReimbursmentid() {
		return reimbursmentid;
	}
	public void setReimbursmentid(int reimbursmentid) {
		this.reimbursmentid = reimbursmentid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp timestamp) {
		this.submitted = timestamp;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getResolver() {
		return resolver;
	}
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Reimbursment [reimbursmentid=" + reimbursmentid + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
}