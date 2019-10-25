package com.mall.common.share.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User: lanxinghua
 * Date: 2019/10/25 13:37
 * Desc: 公共查询对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuery implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final int MAX_PAGE_SIZE = 1000;

    protected int pageSize;
}
