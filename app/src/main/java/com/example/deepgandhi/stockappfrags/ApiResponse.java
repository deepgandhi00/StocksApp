package com.example.deepgandhi.stockappfrags;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import retrofit2.Response;

/**
 * Common class used by API responses.
 *
 * @param <T>
 */
public class ApiResponse<T> {
//    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);
    private static final Pattern LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
    private static final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d)+");
    private static final String NEXT_LINK = "next";
    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public String errorMessage;

    public ApiResponse(Throwable error) {

        code = 500;
        body = null;
        errorMessage = "Something went wrong. Please try again";

    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
//                    Timber.e(ignored, "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }

            body = null;
        }

        String linkHeader = response.headers().get("error_state");
        if (linkHeader != null) {
            errorMessage =linkHeader;
        }
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }


}