package com.mall.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* User: lanxinghua created by mybatis generator
* Date: 2019/10/25 03:03
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsCoupon implements Serializable {
    private Long id;

    @ApiModelProperty(value = "优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    private Integer type;

    private String name;

    @ApiModelProperty(value = "使用平台：0->全部；1->移动；2->PC")
    private Integer platform;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "每人限领张数")
    private Integer perLimit;

    @ApiModelProperty(value = "使用门槛；0表示无门槛")
    private BigDecimal minPoint;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty(value = "使用类型：0->全场通用；1->指定分类；2->指定商品")
    private Integer useType;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "发行数量")
    private Integer publishCount;

    @ApiModelProperty(value = "已使用数量")
    private Integer useCount;

    @ApiModelProperty(value = "领取数量")
    private Integer receiveCount;

    @ApiModelProperty(value = "可以领取的日期")
    private Date enableTime;

    @ApiModelProperty(value = "优惠码")
    private String code;

    @ApiModelProperty(value = "可领取的会员类型：0->无限时")
    private Integer memberLevel;

    private static final long serialVersionUID = 1L;
}
