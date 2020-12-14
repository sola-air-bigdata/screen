package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 10:27
 * Describe:
 */
@Entity(name = "")
public class StationShowMemberBalance {

    @Id
    //	'会员id'
    private BigInteger	l_member_id;
    //	'站点id'
    private BigInteger	l_station_id;
    //	'累计消费金额'
    private BigInteger total_balance_count;
}
