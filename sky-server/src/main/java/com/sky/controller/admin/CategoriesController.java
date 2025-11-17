package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoriesService;
import com.sky.service.impl.CategoriesServiceImpl;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RequestMapping("/admin/category")
@RestController
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    /**
     * 新增菜品
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result AddCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);

        categoriesService.AddCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result DeleteCategoryById(long id) {
        log.info("根据id删除分类：{}",id);

        categoriesService.DeleteCategoryById(id);
        return Result.success();
    }

    /**
     * 启用禁用类别
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result StartOrStop(@PathVariable Integer status, long id) {
        log.info("启用禁用类别：{}，{}",status,id);

        categoriesService.StartOrStop(status, id);
        return Result.success();
    }

    /**
     * 分页分类查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> PageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分类分页查询：{}", categoryPageQueryDTO);

        PageResult pageResult = categoriesService.PageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    public Result UpdateCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);

        categoriesService.UpdateCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 根据类型分类查询
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> GetCategoriesByType(Integer type) {
        log.info("根据分类查询：{}", type);

        List<Category> records = categoriesService.GetCategoriesByType(type);
        return Result.success(records);
    }
}
