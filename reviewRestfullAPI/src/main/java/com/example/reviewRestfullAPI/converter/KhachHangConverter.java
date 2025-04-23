package com.example.reviewRestfullAPI.converter;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.reviewRestfullAPI.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.reviewRestfullAPI.repository.KhachHangRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KhachHangConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    KhachHangRepository khachHangRepository;

    public ViewKhachHangDTO convertToDTO(KhachHang khachHang){
        ViewKhachHangDTO dto= modelMapper.map(khachHang,ViewKhachHangDTO.class);
        return dto;
    }

    public KhachHang convertToEntity(CreateKhachHangDTO createKhachHangDTO) throws ConvertEntityDTOException {
        try{
            KhachHang khachHang= modelMapper.map(createKhachHangDTO,KhachHang.class);
            return khachHang;
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewKhachHangDTO> convertToListDTO(List<KhachHang> listEntity) throws ConvertEntityDTOException {
        List<ViewKhachHangDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
