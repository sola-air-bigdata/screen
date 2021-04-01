package com.nzkj.screen.mapper.pile.member;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liu yang
 * @Date: 2021/3/31 17:38
 * Describe:
 */
@Repository
public interface IMemberMapper  extends BaseMapper<Member> {

    @Select("select * from t_member where deleted = 0 and id = #{id}")
    Member get(@Param("id") long id);

}
