package org.mengnankk.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体类
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    private Long id;

    /** 用户名 */
    private String username;

    /** 姓名 */
    private String name;

    /** 密码 */
    private String password;

    /** 手机号码 */
    private String phone;

    /** 性别 */
    private String sex;

    /** 身份证号码 */
    private String idNumber;

    /** 员工状态：1为在职 */
    private Integer status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT) // 自动填充，插入时自动填充
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE) // 自动填充，插入和更新时自动填充
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT) // 自动填充，插入时自动填充
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE) // 自动填充，插入和更新时自动填充
    private Long updateUser;
}
