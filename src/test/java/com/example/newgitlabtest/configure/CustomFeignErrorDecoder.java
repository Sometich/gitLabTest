package com.example.newgitlabtest.configure;

import feign.Response;
import feign.codec.ErrorDecoder;

public final class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(final String methodKey, final Response response) {
        return new IllegalArgumentException("" + response.status());
    }
}
