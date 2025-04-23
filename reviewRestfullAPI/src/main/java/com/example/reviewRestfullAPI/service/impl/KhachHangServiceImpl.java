package com.example.reviewRestfullAPI.service.impl;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.converter.KhachHangConverter;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reviewRestfullAPI.repository.KhachHangRepository;
import com.example.reviewRestfullAPI.service.KhachHangService;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangConverter khachHangConverter;
    @Autowired
    KhachHangRepository khachHangRepository;

    private static final Logger LOGGER= LoggerFactory.getLogger(KhachHangServiceImpl.class);

    @Override
    public KhachHang createKhachHang(CreateKhachHangDTO createKhachHangDTO) throws CreateDataFailException {
        KhachHang khachHang;
        try{
            khachHang=khachHangConverter.convertToEntity(createKhachHangDTO);
            return khachHangRepository.save(khachHang);
        }catch (Exception e){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
        }
    }

    @Override
    public KhachHang updateKhachHang(ViewKhachHangDTO viewKhachHangDTO, Long id) throws UpdateDataFailException {
        try{
            Optional<KhachHang> opt= khachHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= opt.get();
            khachHang.setTenKhachHang(viewKhachHangDTO.getTenKhachHang());
            khachHang.setEmail(viewKhachHangDTO.getEmail());
            khachHang.setSdt(viewKhachHangDTO.getSdt());
            khachHang.setGioiTinh(viewKhachHangDTO.isGioiTinh());
            khachHang.setDiemTichLuy(viewKhachHangDTO.getDiemTichLuy());
            khachHang.setNgaySinh(viewKhachHangDTO.getNgaySinh());

            return khachHangRepository.save(khachHang);
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
        }

    }

    @Override
    public void deleteKhachHang(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<KhachHang> opt = khachHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHangRepository.deleteById(id);
        } catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
        }
    }

    @Override
    public ViewKhachHangDTO getKhachHangById(Long id) throws DataNotFoundException {
        ViewKhachHangDTO viewKhachHangDTO;
        try{
            Optional<KhachHang> optKhachHang= khachHangRepository.findById(id);
            if(!optKhachHang.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= optKhachHang.get();
            viewKhachHangDTO= khachHangConverter.convertToDTO(khachHang);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return viewKhachHangDTO;
    }

    @Override
    public List<ViewKhachHangDTO> getListKhachHang() throws DataNotFoundException {
        List<ViewKhachHangDTO> listDTO;
        try{
            List<KhachHang> listEntity= khachHangRepository.findAll();
            listDTO=khachHangConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
        }
        return listDTO;
    }


    @Override
    public Boolean getKHBySdt(String sdt) throws DataNotFoundException {
        ViewKhachHangDTO viewKhachHangDTO;
        try{
            Optional<KhachHang> optKhachHang= khachHangRepository.getKHTheoSdt(sdt);
            if(!optKhachHang.isPresent()){
                return false;
            }
        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return true;
    }
}
