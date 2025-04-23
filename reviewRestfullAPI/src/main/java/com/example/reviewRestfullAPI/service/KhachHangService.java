package com.example.reviewRestfullAPI.service;

import com.example.reviewRestfullAPI.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;

import java.util.List;

public interface KhachHangService {
    KhachHang createKhachHang(CreateKhachHangDTO createKhachHangDTO) throws CreateDataFailException;
    KhachHang updateKhachHang(ViewKhachHangDTO viewKhachHangDTO, Long id) throws UpdateDataFailException;
    void deleteKhachHang(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewKhachHangDTO getKhachHangById(Long id) throws DataNotFoundException;
    List<ViewKhachHangDTO> getListKhachHang() throws DataNotFoundException;
    Boolean getKHBySdt(String sdt) throws DataNotFoundException;
}
