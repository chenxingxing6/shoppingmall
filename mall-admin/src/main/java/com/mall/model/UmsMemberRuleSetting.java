package com.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class UmsMemberRuleSetting implements Serializable {
    private Long id;

    @ApiModelProperty(value = "连续签到天数")
    private Integer continueSignDay;

    @ApiModelProperty(value = "连续签到赠送数量")
    private Integer continueSignPoint;

    @ApiModelProperty(value = "每消费多少元获取1个点")
    private BigDecimal consumePerPoint;

    @ApiModelProperty(value = "最低获取点数的订单金额")
    private BigDecimal lowOrderAmount;

    @ApiModelProperty(value = "每笔订单最高获取点数")
    private Integer maxPointPerOrder;

    @ApiModelProperty(value = "类型：0->积分规则；1->成长值规则")
    private Integer type;

    private static final long serialVersionUID = 1L;
}