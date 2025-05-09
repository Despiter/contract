package com.martin.contract.common;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> extends Request<T> {

    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;

}
