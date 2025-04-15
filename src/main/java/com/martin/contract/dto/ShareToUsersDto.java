package com.martin.contract.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShareToUsersDto {

    @NotNull
    private Long objId;

    @NotEmpty
    private List<Long> sharedUserIds;

}
