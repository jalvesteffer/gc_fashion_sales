package com.smoothstack.gcfashion.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import com.smoothstack.gcfashion.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CouponDAO extends JpaRepository<Coupon, Long> {
	
}
