package com.mall.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* User: lanxinghua created by mybatis generator
* Date: 2019/10/25 03:03
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CmsMemberReport implements Serializable {
    private Long id;

    @ApiModelProperty(value = "举报类型：0->商品评价；1->话题内容；2->用户评论")
    private Integer reportType;

    @ApiModelProperty(value = "举报人")
    private String reportMemberName;

    private Date createTime;

    private String reportObject;

    @ApiModelProperty(value = "举报状态：0->未处理；1->已处理")
    private Integer reportStatus;

    @ApiModelProperty(value = "处理结果：0->无效；1->有效；2->恶意")
    private Integer handleStatus;

    private String note;

    private static final long serialVersionUID = 1L;
}
