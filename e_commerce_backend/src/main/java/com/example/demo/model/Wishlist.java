package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(targetEntity = Product.class,fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	private Date createdDate;

	public Wishlist(Integer id, User user, Product product, Date createdDate) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.createdDate = createdDate;
	}

	public Wishlist() {
	}

	public Wishlist(User user, Product product) {
		this.user = user;
		this.product = product;
		this.createdDate= new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
	
	
	
	
}
