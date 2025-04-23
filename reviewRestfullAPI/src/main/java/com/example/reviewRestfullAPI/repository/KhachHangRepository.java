package com.example.reviewRestfullAPI.repository;

import com.example.reviewRestfullAPI.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    @Query(value = "SELECT e.* FROM KhachHang e WHERE e.sdt = ?1 ",nativeQuery = true)
    Optional<KhachHang> getKHTheoSdt(String sdt);
}
