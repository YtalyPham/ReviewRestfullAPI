package com.example.reviewRestfullAPI.service;

import com.example.reviewRestfullAPI.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.reviewRestfullAPI.entity.HoaDon;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;

import java.util.List;

public interface HoaDonService {
    HoaDon createHoaDon(CreateHoaDonDTO createHoaDonDTO) throws CreateDataFailException;
    HoaDon updateHoaDon(ViewHoaDonDTO viewHoaDonDTO,Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteHoaDon(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewHoaDonDTO getHoaDonById(Long id) throws DataNotFoundException;
    List<ViewHoaDonDTO> getListHoaDon() throws DataNotFoundException;

}
