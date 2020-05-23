package com.qa.allure;

import io.qameta.allure.Attachment;

public class AttachmentUtils {

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachLog(final String name, final String data) {
        return data;
    }
}
