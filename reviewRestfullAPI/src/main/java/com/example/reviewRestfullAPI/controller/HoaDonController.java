package com.example.reviewRestfullAPI.controller;


import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.constants.SuccessCode;
import com.example.reviewRestfullAPI.converter.HoaDonConverter;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.reviewRestfullAPI.dto.ResponseDTO.ResponseDTO;
import com.example.reviewRestfullAPI.entity.HoaDon;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.reviewRestfullAPI.service.HoaDonService;

import java.util.List;
import org.slf4j.Logger;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hoadon")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    HoaDonConverter hoaDonConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoaDonController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllHoaDon() throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<ViewHoaDonDTO> listDTO= hoaDonService.getListHoaDon();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HOADON_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list HoaDon: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getHoaDonById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            ViewHoaDonDTO hoaDonDTO= hoaDonService.getHoaDonById(id);
            if(hoaDonDTO!=null){
                responseDTO.setData(hoaDonDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HOADON_FOUND);
            }else{
                responseDTO.setData(hoaDonDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list HoaDon: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HOADON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewHoaDon(@RequestBody CreateHoaDonDTO createHoaDonDTO)  {
        ResponseDTO responseDTO= new ResponseDTO();
        try {
            HoaDon hoaDon= hoaDonService.createHoaDon(createHoaDonDTO);
            ViewHoaDonDTO viewHoaDonDTO= hoaDonConverter.convertToDto(hoaDon);
            responseDTO.setData(viewHoaDonDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_HOADON);
        }catch (Exception e){
            LOGGER.info("Lỗi khi tạo HoaDon: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_HOADON_FAIL);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateHoaDon(@RequestBody ViewHoaDonDTO viewHoaDonDTO) throws UpdateDataFailException{
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            HoaDon hoaDon = hoaDonService.updateHoaDon(viewHoaDonDTO, viewHoaDonDTO.getMaHoaDon());
            ViewHoaDonDTO dto = hoaDonConverter.convertToDto(hoaDon);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_HOADON);
        }catch (Exception e){
            LOGGER.info("Lỗi khi cập nhật HoaDon: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_HOADON_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteHoaDon(@PathVariable Long id) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            hoaDonService.deleteHoaDon(id);

            responseDTO.setData("Xoa hoa don thanh cong id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_HOADON);
        }catch (Exception e){
            LOGGER.info("Lỗi khi xóa HoaDon: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_HOADON_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_HOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
