package dev.be.pongdang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pongdang.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
