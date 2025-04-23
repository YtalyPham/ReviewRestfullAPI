package com.example.reviewRestfullAPI.repository;

import com.example.reviewRestfullAPI.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn,Long> {
}
