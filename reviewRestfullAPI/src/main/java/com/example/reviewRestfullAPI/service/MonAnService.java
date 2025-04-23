package com.example.reviewRestfullAPI.service;

import com.example.reviewRestfullAPI.dto.MonAnDTO.CreateMonAnDTO;
import com.example.reviewRestfullAPI.dto.MonAnDTO.ViewMonAnDTO;
import com.example.reviewRestfullAPI.entity.MonAn;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;

import java.util.List;

public interface MonAnService {
    MonAn createMonAn(CreateMonAnDTO createMonAnDTO) throws CreateDataFailException;
    MonAn updateMonAn(ViewMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteMonAn(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewMonAnDTO getMonAnById(Long id) throws DataNotFoundException;
    List<ViewMonAnDTO> getListMonAn() throws DataNotFoundException;

}
