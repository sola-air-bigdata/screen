package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.OperatingUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Liu yang
 * @Date: 2020/7/3 16:24
 * Describe:
 */
public interface OperatingUnitRepository extends JpaRepository<OperatingUnit,Long> {

    //OperatingUnit findById(long id);


}
