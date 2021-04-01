package com.nzkj.screen.Entity.DTO;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2021/3/31 17:08
 * Describe:
 */
public class StationShowMonthMemberDTO {

    private Long LMemberId;

    private BigInteger MonthTeamBalanceCount;

    private BigInteger MonthMemberChargeCount;

    public Long getLMemberId() {
        return LMemberId;
    }

    public void setLMemberId(Long LMemberId) {
        this.LMemberId = LMemberId;
    }

    public BigInteger getMonthTeamBalanceCount() {
        return MonthTeamBalanceCount;
    }

    public void setMonthTeamBalanceCount(BigInteger monthTeamBalanceCount) {
        MonthTeamBalanceCount = monthTeamBalanceCount;
    }

    public BigInteger getMonthMemberChargeCount() {
        return MonthMemberChargeCount;
    }

    public void setMonthMemberChargeCount(BigInteger monthMemberChargeCount) {
        MonthMemberChargeCount = monthMemberChargeCount;
    }
}
