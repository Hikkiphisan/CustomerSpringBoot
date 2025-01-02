package org.example.springbootcustomermanagement.repository;

import org.example.springbootcustomermanagement.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public interface IProvinceRepository extends JpaRepository<Province, Long> {


}
