package com.mall.dao;

import com.mall.dto.PmsProductResult;
import com.mall.model.CmsPrefrenceAreaProductRelation;
import com.mall.model.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;


/**
 * 商品自定义Dao
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);

    CmsSubjectProductRelation selectSubjectProductRelationByProductId(@Param("productId") String productId);

    CmsPrefrenceAreaProductRelation selectPrefrenceAreaProductRelationByProductId(@Param("productId") String productId);
}
