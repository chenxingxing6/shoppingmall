package com.mall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* User: lanxinghua created by mybatis generator
* Date: 2019/10/25 03:03
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PmsAlbumPic implements Serializable {
    private Long id;

    private Long albumId;

    private String pic;

    private static final long serialVersionUID = 1L;
}
