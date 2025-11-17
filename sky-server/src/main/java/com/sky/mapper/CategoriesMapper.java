package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUES" +
            "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void AddCategory(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("DELETE from category where id = #{id}")
    void DeleteCategoryById(long id);

    /**
     * 启用禁用类别
     * @param category
     */
    void Update(Category category);

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> PageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据类别查询分类
     * @param type
     * @return
     */
    @Select("select * from category where type = #{type}")
    List<Category> GetCategoriesByType(Integer type);
}
