package org.example.springbootcustomermanagement.service;

import org.example.springbootcustomermanagement.model.Province;


public interface IProvinceService extends IGenerateService<Province> {
     void deleteProvincebyProcedure(Long provinceId);

}