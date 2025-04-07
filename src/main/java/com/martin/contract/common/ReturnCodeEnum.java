package com.martin.contract.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {

    /**
     * 操作失败
     **/
    RC999("999", "操作XXX失败"),

    /**
     * 操作成功
     **/
    RC200("200", "success"),

    /**
     * 服务降级
     **/
    RC201("201", "服务器开起降级保护,请稍后再试"),

    /**
     * 热点参数限流
     **/
    RC202("202", "热点参数限流,请稍后再试"),

    /**
     * 系统规则不满足
     **/
    RC203("203", "系统规则不满足要求,请稍后再试"),

    /**
     * 授权规则不通过
     **/
    RC204("204", "授权规则不通过,请稍后再试"),

    /**
     * 未登录
     */
    RC300("300", "账号或密码错误"),

    /**
     * access_denied
     **/
    RC401("401", "无效token"),
    RC403("403", "无访问权限,请联系管理员授予权限"),
    RC404("404", "404页面找不到的异常"),

    /**
     * 服务异常
     **/
    RC500("500", "系统异常,请稍后重试"),
    RC375("375", "数学运算异常,请稍后重试"),

    INVALID_TOKEN("2001", ""),
    ACCESS_DENIED("999", ""),
    ;

    private final String code;
    private final String msg;

}
