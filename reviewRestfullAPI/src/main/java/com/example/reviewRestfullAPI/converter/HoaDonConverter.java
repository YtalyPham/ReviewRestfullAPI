package com.example.reviewRestfullAPI.converter;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.reviewRestfullAPI.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.reviewRestfullAPI.entity.HoaDon;
import com.example.reviewRestfullAPI.entity.KhachHang;
import com.example.reviewRestfullAPI.exceptions.ConvertEntityDTOException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.reviewRestfullAPI.repository.HoaDonRepository;
import com.example.reviewRestfullAPI.repository.KhachHangRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HoaDonConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    public ViewHoaDonDTO convertToDto(HoaDon hoaDon){
        ViewHoaDonDTO dto= modelMapper.map(hoaDon, ViewHoaDonDTO.class);
        Long maKh= hoaDon.getKhachHangHD().getMaKhachHang();

        dto.setMaKhachHangHD(maKh);
        return dto;
    }

    public HoaDon convertToEntity(CreateHoaDonDTO createHoaDonDTO) throws ConvertEntityDTOException{
        try {
            HoaDon hoaDon= modelMapper.map(createHoaDonDTO,HoaDon.class);

            Optional<KhachHang> opt= khachHangRepository.findById(createHoaDonDTO.getMaKhachHang());
            KhachHang khachHang;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);

            }
            khachHang=opt.get();
            hoaDon.setKhachHangHD(khachHang);
            return hoaDon;
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewHoaDonDTO> convertToListDTO(List<HoaDon> listEntity) throws ConvertEntityDTOException{
        List<ViewHoaDonDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDto).collect(Collectors.toList());
        }catch(Exception e){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }

}
