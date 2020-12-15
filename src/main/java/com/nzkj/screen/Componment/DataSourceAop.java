package com.nzkj.screen.Componment;


import com.nzkj.screen.Entity.enums.DataSourceEnum;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -99)
@Aspect
@Log4j2
/** @Description 数据源Aop类 @Author gdq @Date 2020/7/31 14:32 @Version 1.0 */
public class DataSourceAop {

  @Pointcut("execution(* com.nzkj.screen.mapper.pile.config..*.*(..))")
  public void config() {}

  @Pointcut("execution(* com.nzkj.screen.mapper.pile.member..*.*(..))")
  public void member() {}

  @Pointcut("execution(* com.nzkj.screen.mapper.pile.bill..*.*(..))")
  public void bill() {}

  @Pointcut("execution(* com.nzkj.screen.mapper.pile.screen..*.*(..))")
  public void screen() {}

  @Around("config()")
  public Object configAround(ProceedingJoinPoint joinPoint) {
    try {
      DataSourceContext.setCurrent(DataSourceEnum.PILE_CONFIG);
      return joinPoint.proceed();
    } catch (Throwable e) {
      log.error("查询配置数据库出现异常", e);
    } finally {
      DataSourceContext.clear();
    }
    return null;
  }

  @Around("member()")
  public Object memberAround(ProceedingJoinPoint joinPoint) {
    try {
      DataSourceContext.setCurrent(DataSourceEnum.PILE_MEMBER);
      return joinPoint.proceed();
    } catch (Throwable e) {
      log.error("查询会员数据库出现异常", e);
    } finally {
      DataSourceContext.clear();
    }
    return null;
  }

  @Around("bill()")
  public Object billAround(ProceedingJoinPoint joinPoint) {
    try {
      DataSourceContext.setCurrent(DataSourceEnum.PILE_BILL);
      return joinPoint.proceed();
    } catch (Throwable e) {
      log.error("查询账单数据库出现异常", e);
    } finally {
      DataSourceContext.clear();
    }
    return null;
  }

  @Around("screen()")
  public Object screenAround(ProceedingJoinPoint joinPoint) {
    try {
      DataSourceContext.setCurrent(DataSourceEnum.PILE_SCREEN);
      return joinPoint.proceed();
    } catch (Throwable e) {
      log.error("查询大屏汇总数据库出现异常", e);
    } finally {
      DataSourceContext.clear();
    }
    return null;
  }
}
