package com.example.reviewRestfullAPI.converter;
 

 
import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.dto.MonAnDTO.ViewMonAnDTO;
import com.example.reviewRestfullAPI.dto.MonAnDTO.CreateMonAnDTO;
import com.example.reviewRestfullAPI.entity.MonAn;
import com.example.reviewRestfullAPI.exceptions.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.reviewRestfullAPI.repository.MonAnRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonAnConverter {
    @Autowired
    MonAnRepository monAnRepository;
    @Autowired
    ModelMapper modelMapper;

    public ViewMonAnDTO convertToDTO(MonAn monAn){
        ViewMonAnDTO dto= modelMapper.map(monAn,ViewMonAnDTO.class);
        return dto;
    }
    

    public MonAn convertToEntity(CreateMonAnDTO createMonAnDTO) throws ConvertEntityDTOException {
        try{
            MonAn monAn= modelMapper.map(createMonAnDTO,MonAn.class);
            return monAn;
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewMonAnDTO> convertToListDTO(List<MonAn> listEntity) throws ConvertEntityDTOException {
        List<ViewMonAnDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
