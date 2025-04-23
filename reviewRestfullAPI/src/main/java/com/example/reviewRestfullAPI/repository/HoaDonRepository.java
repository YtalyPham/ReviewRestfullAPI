package com.example.reviewRestfullAPI.repository;

import com.example.reviewRestfullAPI.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {

}
