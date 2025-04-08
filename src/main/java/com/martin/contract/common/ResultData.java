package com.martin.contract.common;

import com.martin.contract.enums.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.martin.contract.enums.ReturnCode.RC200;

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

    public static <T> ResultData<List<T>> emptyCollection() {
        return ResultData.<List<T>>builder().code(RC200.getCode()).msg(RC200.getMsg()).data(Collections.emptyList()).build();
    }

    public static <T> ResultData<T> fail(ReturnCode returnCode) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(returnCode.getCode());
        resultData.setMsg(returnCode.getMsg());
        return resultData;
    }

}
