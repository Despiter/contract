package com.martin.contract.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> {

    private T data;

//    private Long userId;

    private Integer pageNo;

    private Integer pageSize;

}
