package com.weykko.librarysystem.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Instant timestamp;

    private int status;

    private String message;

    private String path;

    private List<Violation> violations;

    @Data
    @AllArgsConstructor
    public static class Violation {
        private String field;
        private String message;
    }
}
