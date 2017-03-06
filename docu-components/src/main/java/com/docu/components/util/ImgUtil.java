package com.docu.components.util;

import org.apache.commons.lang.StringUtils;

public final class ImgUtil {

    private static final String OSS_IMG_URL = "http://www.oocl.com";
    private static final String SPLIT = "/";
    private static final String OSS_SPLIT = "@";
    private static final String OSS_IMG_W = "w";
    private static final String OSS_IMG_H = "h";
    private static final String OSS_IMG_SPLIT = "_";

    private static int wDefault = 100;
    private static int hDefault = 100;

    public static String getImgUrl(int w, int h, String imgName) {
        if (imgName == null) {
            return "";
        }
        if (w == 0) {
            w = wDefault;
        }
        if (h == 0) {
            h = hDefault;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(OSS_IMG_URL);
        builder.append(SPLIT);
        builder.append(imgName);
        builder.append(OSS_SPLIT);
        builder.append(w);
        builder.append(OSS_IMG_W);
        builder.append(OSS_IMG_SPLIT);
        builder.append(h);
        builder.append(OSS_IMG_H);
        builder.append(getFileNameExt(imgName));
        return builder.toString();
    }

    public static String getFileNameExt(String fileName) {
        int pos = StringUtils.lastIndexOf(fileName, ".");
        if (pos > -1)
            return StringUtils.substring(fileName, pos);
        return "";
    }
}
