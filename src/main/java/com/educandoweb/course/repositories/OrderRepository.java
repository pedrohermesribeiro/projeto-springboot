package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

@SuppressWarnings("rawtypes")
public interface OrderRepository extends JpaRepository<Order, Long> {

}

