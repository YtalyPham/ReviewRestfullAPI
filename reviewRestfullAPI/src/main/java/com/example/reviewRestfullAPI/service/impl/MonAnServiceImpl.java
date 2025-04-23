package com.example.reviewRestfullAPI.service.impl;

import com.example.reviewRestfullAPI.constants.ErrorCode;
import com.example.reviewRestfullAPI.converter.MonAnConverter;
import com.example.reviewRestfullAPI.dto.MonAnDTO.CreateMonAnDTO;
import com.example.reviewRestfullAPI.dto.MonAnDTO.ViewMonAnDTO;
import com.example.reviewRestfullAPI.entity.MonAn;
import com.example.reviewRestfullAPI.exceptions.CreateDataFailException;
import com.example.reviewRestfullAPI.exceptions.DataNotFoundException;
import com.example.reviewRestfullAPI.exceptions.DeleteDataFailException;
import com.example.reviewRestfullAPI.exceptions.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reviewRestfullAPI.repository.MonAnRepository;
import com.example.reviewRestfullAPI.service.MonAnService;

import java.util.List;
import java.util.Optional;

@Service
public class MonAnServiceImpl implements MonAnService {
    @Autowired
    MonAnConverter monAnConverter;
    @Autowired
    MonAnRepository monAnRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MonAnServiceImpl.class);

    @Override
    public MonAn createMonAn(CreateMonAnDTO createMonAnDTO) throws CreateDataFailException {
        MonAn monAn;
        try {
            monAn = monAnConverter.convertToEntity(createMonAnDTO);
            return monAnRepository.save(monAn);
        } catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_MONAN_FAIL);
        }
    }

    @Override
    public MonAn updateMonAn(ViewMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try {
            Optional<MonAn> opt = monAnRepository.findById(id);
            if (!opt.isPresent()) {
                LOGGER.info("Mon an khong tim thay: {}", id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn = opt.get();
            monAn.setTenMonAn(viewMonAnDTO.getTenMonAn());
            monAn.setDonGia(viewMonAnDTO.getDonGia());
            return monAnRepository.save(monAn);

        } catch (DataNotFoundException e) {
            String message = e.getMessage();
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
    }


    @Override
    public void deleteMonAn(Long id) throws DeleteDataFailException, DataNotFoundException {
        try {
            Optional<MonAn> opt = monAnRepository.findById(id);
            if (!opt.isPresent()) {
                LOGGER.info("MonAn khong tim thay: {}", id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            monAnRepository.deleteById(id);
        } catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_MONAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_MONAN_FAIL);
        }
    }

    @Override
    public ViewMonAnDTO getMonAnById(Long id) throws DataNotFoundException {
        ViewMonAnDTO viewMonAnDTO;
        try {
            Optional<MonAn> optMonAn = monAnRepository.findById(id);
            if (!optMonAn.isPresent()) {
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn = optMonAn.get();
            viewMonAnDTO = monAnConverter.convertToDTO(monAn);

        } catch (Exception exception) {
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        }
        return viewMonAnDTO;
    }

    @Override
    public List<ViewMonAnDTO> getListMonAn() throws DataNotFoundException {
        List<ViewMonAnDTO> listDTO;
        try {
            List<MonAn> listEntity = monAnRepository.findAll();
            listDTO = monAnConverter.convertToListDTO(listEntity);
        } catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }


}
