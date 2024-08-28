package com.ericsson.eniq.events.server.test.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtilities {

    public static String readInputStream(final InputStream fileInputStream) throws IOException {
        BufferedInputStream bufferedInputStream = null;
        DataInputStream dataInputStream = null;
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);

            while (dataInputStream.available() != 0) {
                stringBuilder.append(dataInputStream.readLine());
                if (dataInputStream.available() != 0) {
                    stringBuilder.append(System.getProperty("line.separator"));

                }
            }

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        }
        return stringBuilder.toString();
    }

    public static String readFileFromClasspath(final String filePath) throws java.io.IOException {
        final InputStream fileInputStream = FileUtilities.class.getClassLoader().getResourceAsStream(filePath);
        return readInputStream(fileInputStream);
    }

}
