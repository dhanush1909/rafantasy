package com.rafantasy.userservice.domain.service;

import com.rafantasy.userservice.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findAllByUserId(Integer userId);
}
