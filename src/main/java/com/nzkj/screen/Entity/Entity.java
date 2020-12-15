package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Entity implements Serializable {
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private Integer deleted;

  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.UPDATE)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime updateTime;

}
