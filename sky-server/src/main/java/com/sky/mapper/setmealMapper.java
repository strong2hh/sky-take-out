package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface setmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param category_id
     * @return
     */
    @Select("select count(*) from setmeal where category_id = #{category_id}")
    Integer countByCategoryId(long category_id);
}
