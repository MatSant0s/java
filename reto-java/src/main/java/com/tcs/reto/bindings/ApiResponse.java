package com.tcs.reto.bindings;

import lombok.Builder;

@Builder
public record ApiResponse(int code, String message, Object data) {
}
