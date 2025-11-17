package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoriesMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.setmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoriesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    public CategoriesMapper categoriesMapper ;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private setmealMapper setmealMapper;

    /**
     * 新增分类
     * @param categoryDTO
     */
    @Override
    public void AddCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoriesMapper.AddCategory(category);
    }

    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void DeleteCategoryById(long id) {
        Integer count = dishMapper.countByCategoryId(id);
        if(count>0){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        count = setmealMapper.countByCategoryId(id);
        if(count>0){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        categoriesMapper.DeleteCategoryById(id);
    }

    /**
     * 启用禁用类别
     * @param status
     * @param id
     */
    @Override
    public void StartOrStop(Integer status, long id) {
        Category category = new Category();
        category.setId(id);
        category.setStatus(status);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoriesMapper.Update(category);
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult PageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {

        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());

        Page<Category> page = categoriesMapper.PageQuery(categoryPageQueryDTO);
        long total = page.getTotal();
        List<Category> categoryList = page.getResult();

        return new PageResult(total,categoryList);
    }

    /**
     * 修改分类
     * @param categoryDTO
     */
    @Override
    public void UpdateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoriesMapper.Update(category);
    }

    /**
     * 根据类别查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> GetCategoriesByType(Integer type) {
        List<Category> records = categoriesMapper.GetCategoriesByType(type);
        return records;
    }
}
