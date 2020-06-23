package com.maxChen.onlineStore.VO;

//http outer object

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    /**
     * error code
     */
    private Integer code;

    /**
     * reminder
     */
    private String msg;

    /**
     * content that return
     */

    private T data;
}
