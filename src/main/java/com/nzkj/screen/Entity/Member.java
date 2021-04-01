package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * @Author: Liu yang
 * @Date: 2021/3/31 17:29
 * Describe:
 */
@TableName("t_member")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member extends Entity {

    private String name;
    private String telephone;
    private BigInteger addUserId;
    private String plateno;
    private String openId;
    private String company;
    private int status;
    private int sellerId;
    private int memberType;
    private int level;
    private int integral;
    private int sex;
    private String icon;
    private int lineOfCredit;
    private String totalConsumption;
    private int singleRemainder;
    private int totalDgree;
    private int signIn;
    private int collect;
    private String miniappsId;
    private int consumeIntegral;
    private int firstUse;
    private String notConfigType;
    private String registerType;
    private String inviterId;
    private String deductMode;
    private int isConnectivity;
    private String channel;
    private int activityBalance;
    private int isFreeAppoint;
    private int defaultNumber;
    private Date punishmentStartDate;
    private Date punishmentDate;
    private int appointNumber;
    private int inviterMemberId;
    private int limitStation;
    private int bSecretFreePayment;
}
