package com.example.reviewRestfullAPI.controller;
import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.constants.SuccessCode;
import com.example.reviewRestfullAPI.converter.MonAnConverter;
import com.example.reviewRestfullAPI.dto.MonAnDTO.CreateMonAnDTO;
import com.example.reviewRestfullAPI.dto.MonAnDTO.ViewMonAnDTO;
import com.example.reviewRestfullAPI.dto.ResponseDTO.ResponseDTO;
import com.example.reviewRestfullAPI.entity.MonAn;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.reviewRestfullAPI.service.MonAnService;

import java.util.List;
import org.slf4j.Logger;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/monan")
public class MonAnController {
    @Autowired
    MonAnService monAnService;
    @Autowired
    MonAnConverter monAnConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(MonAnController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllMonAn() throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<ViewMonAnDTO> listDTO= monAnService.getListMonAn();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list MonAn: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getMonAnById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            ViewMonAnDTO monAnDTO= monAnService.getMonAnById(id);
            if(monAnDTO!=null){
                responseDTO.setData(monAnDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_FOUND);
            }else{
                responseDTO.setData(monAnDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
        }catch(Exception e){
            LOGGER.info("Lỗi khi load list MonAn: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewMonAn(@RequestBody CreateMonAnDTO createMonAnDTO) throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try {
            MonAn monAn= monAnService.createMonAn(createMonAnDTO);
            ViewMonAnDTO viewMonAnDTO= monAnConverter.convertToDTO(monAn);
            responseDTO.setData(viewMonAnDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_MONAN);
        }catch (Exception e){
            LOGGER.info("Lỗi khi tạo MonAn: "+ e);
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_MONAN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateMonAn(@RequestBody ViewMonAnDTO viewMonAnDTO) throws UpdateDataFailException{
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            MonAn monAn = monAnService.updateMonAn(viewMonAnDTO, viewMonAnDTO.getMaMonAn());
            ViewMonAnDTO dto = monAnConverter.convertToDTO(monAn);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_MONAN);
        }catch (Exception e){
            LOGGER.info("Lỗi khi cập nhật MonAn: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_MONAN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteMonAn(@PathVariable Long id) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            monAnService.deleteMonAn(id);

            responseDTO.setData("Xoa hoa don thanh cong id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_MONAN);
        }catch (Exception e){
            LOGGER.info("Lỗi khi xóa MonAn: "+ e.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_MONAN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
