package dev.be.pongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pongdang.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
