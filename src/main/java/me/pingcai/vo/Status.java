package me.pingcai.vo;

/**
 * @author huangpingcai
 * @since 2019/4/23 23:42
 */
public enum Status {
    /**
     * 成功
     */
    SUCCESS,
    /**
     * 失败（可重试）
     */
    FAIL,
    /**
     * 异常（不可重试）
     */
    ERROR
}
