package com.nzkj.screen.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Liu yang
 * @Date: 2021/1/7 10:35
 * Describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GunRedisKeyDTO {

    /**
     * 充电枪号
     */
    private String gunNo;

    /**
     * 充电桩ID
     */
    private Long pileId;

    /**
     * 站点ID
     */
    private String stationId;
}
