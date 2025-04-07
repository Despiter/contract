package com.martin.contract.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request<T> {

    private T data;

    private Integer pageNo;

    private Integer pageSize;

}
