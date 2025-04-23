package com.example.reviewRestfullAPI.service.impl;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.converter.HoaDonConverter;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.reviewRestfullAPI.entity.HoaDon;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reviewRestfullAPI.repository.HoaDonRepository;
import com.example.reviewRestfullAPI.repository.KhachHangRepository;
import com.example.reviewRestfullAPI.service.HoaDonService;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    HoaDonConverter hoaDonConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoaDonServiceImpl.class);


    @Override
    public HoaDon createHoaDon(CreateHoaDonDTO createHoaDonDTO) throws CreateDataFailException {
        HoaDon hoaDon;
        try {
            hoaDon = hoaDonConverter.convertToEntity(createHoaDonDTO);
            return hoaDonRepository.save(hoaDon);
        } catch (ConvertEntityDTOException e) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_HOADON_FAIL);
        }


    }

    @Override
    public HoaDon updateHoaDon(ViewHoaDonDTO viewHoaDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try {
            Optional<HoaDon> opt = hoaDonRepository.findById(id);
            if (!opt.isPresent()) {
                LOGGER.info("HoaDon khong tim thay: {}", id);
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            HoaDon hoaDon = opt.get();
            hoaDon.setNgayLapHoaDon(viewHoaDonDTO.getNgayLapHoaDon());

            Optional<KhachHang> opt2 = khachHangRepository.findById(viewHoaDonDTO.getMaKhachHangHD());
            if (!opt2.isPresent()) {
                LOGGER.info("KhachHang khong tim thay: {}", id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang = opt2.get();
            hoaDon.setKhachHangHD(khachHang);
            return hoaDonRepository.save(hoaDon);
        } catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            } else {
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HOADON_FAIL);
        }


    }

    @Override
    public void deleteHoaDon(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<HoaDon> opt= hoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("HoaDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            hoaDonRepository.deleteById(id);
        } catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_HOADON_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_HOADON_FAIL);
        }

    }

    @Override
    public ViewHoaDonDTO getHoaDonById(Long id) throws DataNotFoundException {
        ViewHoaDonDTO viewHoaDonDTO;

        try{
            Optional<HoaDon> opt= hoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Khong tim thay hoa don: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            HoaDon hoaDon= opt.get();
            viewHoaDonDTO = hoaDonConverter.convertToDto(hoaDon);
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
        }
        return viewHoaDonDTO;

    }

    @Override
    public List<ViewHoaDonDTO> getListHoaDon() throws DataNotFoundException {
        List<ViewHoaDonDTO> listDTO;
        try{
            List<HoaDon> listEntity= hoaDonRepository.findAll();
            listDTO=hoaDonConverter.convertToListDTO(listEntity);
        }catch (Exception e){
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
        }

        return listDTO;
    }
}
