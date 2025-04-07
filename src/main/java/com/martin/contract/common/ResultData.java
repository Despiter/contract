package com.martin.contract.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.martin.contract.common.ReturnCodeEnum.RC200;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultData<T> {

    private String code;

    private String msg;

    private T data;

    @Builder.Default
    private long timestamp = System.currentTimeMillis();

    public static <T> ResultData<T> success() {
        return ResultData.<T>builder().code(RC200.getCode()).msg(RC200.getMsg()).build();
    }

    public static <T> ResultData<T> of(T data) {
        return ResultData.<T>builder().code(RC200.getCode()).msg(RC200.getMsg()).data(data).build();
    }

    public static <T> ResultData<T> fail(ReturnCodeEnum returnCodeEnum) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(returnCodeEnum.getCode());
        resultData.setMsg(returnCodeEnum.getMsg());
        return resultData;
    }

}
