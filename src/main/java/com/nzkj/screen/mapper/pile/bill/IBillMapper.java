package com.nzkj.screen.mapper.pile.bill;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.nzkj.screen.Entity.Bill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/** @Description TODO @Author gdq @Date 2020/7/31 13:49 @Version 1.0 */
@Repository
public interface IBillMapper extends BaseMapper<Bill> {

  @Select("select * from t_bill where id = #{id}")
  Bill get(@Param("id") Long id);
}
