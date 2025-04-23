package com.example.reviewRestfullAPI.controller;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.constants.SuccessCode;
import com.example.reviewRestfullAPI.converter.KhachHangConverter;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.reviewRestfullAPI.dto.ResponseDTO.ResponseDTO;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.reviewRestfullAPI.service.KhachHangService;

import java.util.List;
import org.slf4j.Logger;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    KhachHangConverter khachHangConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(KhachHangController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllKhachHang() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<ViewKhachHangDTO> listDTO= khachHangService.getListKhachHang();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list KhachHang: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getKhachHangById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            ViewKhachHangDTO khachHangDTO= khachHangService.getKhachHangById(id);
            if(khachHangDTO!=null){
                responseDTO.setData(khachHangDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }else{
                responseDTO.setData(khachHangDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list KhachHang: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/sdt/{id}")
    public ResponseEntity<ResponseDTO> getKhachHangById(@PathVariable String id) throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            Boolean khachHangDTO= khachHangService.getKHBySdt(id);
            if(khachHangDTO!=null){
                responseDTO.setData(true);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi tim KhachHang by sdt: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewKhachHang(@RequestBody CreateKhachHangDTO createKhachHangDTO) throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try {
            KhachHang khachHang= khachHangService.createKhachHang(createKhachHangDTO);
            ViewKhachHangDTO viewKhachHangDTO= khachHangConverter.convertToDTO(khachHang);
            responseDTO.setData(viewKhachHangDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_KHACHHANG);
        }catch (Exception e){
            LOGGER.info("Lỗi khi tạo KhachHang: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateKhachHang(@RequestBody ViewKhachHangDTO viewKhachHangDTO) throws UpdateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            KhachHang khachHang = khachHangService.updateKhachHang(viewKhachHangDTO, viewKhachHangDTO.getMaKhachHang());
            ViewKhachHangDTO dto = khachHangConverter.convertToDTO(khachHang);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_KHACHHANG);
        }catch (Exception e){
            LOGGER.info("Lỗi khi cập nhật KhachHang: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteKhachHang(@PathVariable Long id) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            khachHangService.deleteKhachHang(id);

            responseDTO.setData("Xoa hoa don thanh cong id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_KHACHHANG);
        }catch (Exception e){
            LOGGER.info("Lỗi khi xóa KhachHang: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
