package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import com.example.demo.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

	List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
