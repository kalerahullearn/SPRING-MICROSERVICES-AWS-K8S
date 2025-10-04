package com.k8s.common.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseEvent<T> implements Serializable {
    private boolean success;
    private int statusCode;
    private List<String> errors;
    private T data;

}
