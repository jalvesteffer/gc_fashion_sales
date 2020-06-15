package com.smoothstack.gcfashion.dao;
import org.springframework.stereotype.Repository;

import com.smoothstack.gcfashion.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{

}