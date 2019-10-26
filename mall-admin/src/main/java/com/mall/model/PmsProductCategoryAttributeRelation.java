package com.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* User: lanxinghua created by mybatis generator
* Date: 2019/10/25 03:03
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductCategoryAttributeRelation implements Serializable {
    private Long id;

    private Long productCategoryId;

    private Long productAttributeId;

    private static final long serialVersionUID = 1L;
}