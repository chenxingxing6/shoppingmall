package com.mall.controller;

import com.mall.common.share.result.CommonPage;
import com.mall.common.share.result.CommonResult;
import com.mall.dto.PmsProductAttributeCategoryItem;
import com.mall.model.PmsProductAttributeCategory;
import com.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分类Controller
 */
@Controller
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductAttributeCategory> getItem(@PathVariable Long id) {
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return CommonResult.success(productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryList));
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(productAttributeCategoryResultList);
    }
}
