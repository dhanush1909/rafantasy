package com.acyuta.userservice.domain.service;

import com.acyuta.userservice.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findAllByUserId(Integer userId);
}
