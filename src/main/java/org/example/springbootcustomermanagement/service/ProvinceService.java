package org.example.springbootcustomermanagement.service;

import org.example.springbootcustomermanagement.model.Province;
import org.example.springbootcustomermanagement.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    private IProvinceRepository iProvinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return iProvinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return iProvinceRepository.findById(id);
    }


    @Override
    public void save(Province province) {
        iProvinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        iProvinceRepository.deleteById(id);
    }



    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Xóa tỉnh sử dụng stored procedure
    public void deleteProvincebyProcedure(Long provinceId) {
        String sql = "CALL deleteProvince(?)";
        jdbcTemplate.update(sql, provinceId);
    }
}


