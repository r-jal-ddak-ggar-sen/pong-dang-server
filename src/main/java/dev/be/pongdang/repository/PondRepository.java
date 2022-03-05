package dev.be.pongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pongdang.entity.Pond;

public interface PondRepository extends JpaRepository<Pond, Long> {

}
