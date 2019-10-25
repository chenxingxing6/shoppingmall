package com.mall.dto;

import com.mall.model.UmsPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User: lanxinghua
 * Date: 2019/10/25 16:01
 * Desc:
 */
public class UmsPermissionNode extends UmsPermission {
    @Setter
    @Getter
    private List<UmsPermissionNode> children;
}
