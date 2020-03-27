package com.sungness.code.generate.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


public class FileHelper {
    public static String readFile(String paramString1, String paramString2) {
        try {
            StringBuffer localStringBuffer = new StringBuffer();
            BufferedReader localBufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(paramString1),
                            paramString2));
            String str;
            while ((str = localBufferedReader.readLine()) != null)
                localStringBuffer.append(str + "\r\n");
            localBufferedReader.close();
            return localStringBuffer.toString();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return "";
    }

    public static String getCharset(File paramFile) {
        String str = "GBK";
        byte[] arrayOfByte = new byte[3];
        BufferedInputStream localBufferedInputStream = null;
        try {
            int i = 0;
            localBufferedInputStream = new BufferedInputStream(new FileInputStream(paramFile));
            localBufferedInputStream.mark(0);
            int j = localBufferedInputStream.read(arrayOfByte, 0, 3);
            if (j == -1) {
                return str;
            }
            if ((arrayOfByte[0] == -1) && (arrayOfByte[1] == -2)) {
                str = "UTF-16LE";
                i = 1;
            } else if ((arrayOfByte[0] == -2) && (arrayOfByte[1] == -1)) {
                str = "UTF-16BE";
                i = 1;
            } else if ((arrayOfByte[0] == -17) && (arrayOfByte[1] == -69)
                    && (arrayOfByte[2] == -65)) {
                str = "UTF-8";
                i = 1;
            }
            localBufferedInputStream.reset();
            if (i == 0) {
                while ((j = localBufferedInputStream.read()) != -1) {
                    if ((j >= 240) || ((128 <= j) && (j <= 191)))
                        break;
                    if ((192 <= j) && (j <= 223)) {
                        j = localBufferedInputStream.read();
                        if ((128 > j) || (j > 191))
                            break;
                        continue;
                    } else {
                        if ((224 > j) || (j > 239))
                            continue;
                        j = localBufferedInputStream.read();
                        if ((128 > j) || (j > 191))
                            break;
                        j = localBufferedInputStream.read();
                        if ((128 > j) || (j > 191))
                            break;
                        str = "UTF-8";
                    }
                }
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        } finally {
            if (localBufferedInputStream != null) {
                try {
                    localBufferedInputStream.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }
        return str;
    }

    public static void backupFile(String paramString) {
        File localFile1 = new File(paramString);
        if (localFile1.exists()) {
            String str1 = paramString + ".001";
            File localFile2 = new File(str1);
            int i = 1;
            while (localFile2.exists()) {
                i++;
                int j = 3 - String.valueOf(i).length();
                String str2 = String.valueOf(i);
                for (int k = 0; k < j; k++)
                    str2 = "0" + str2;
                str1 = paramString + "." + str2;
                localFile2 = new File(str1);
            }
            copyFile(paramString, str1);
        }
    }

    public static void writeFile(File paramFile, String paramString1,
            String paramString2) throws IOException {
        OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(
                new FileOutputStream(paramFile), paramString1);
        localOutputStreamWriter.write(paramString2);
        localOutputStreamWriter.close();
    }

    public static void main(String[] paramArrayOfString) {
        String str1 = "<!--insert-->";
        String str2 = "aa<!--insert-->bb<!--insert-->cc";
        int i = str2.lastIndexOf(str1);
        System.out.println(str2.substring(0, i));
        System.out.println(str2.substring(i + str1.length()));
    }

    public static String[] getBySplit(String paramString1, String paramString2) {
        String[] arrayOfString = new String[2];
        int i = paramString1.lastIndexOf(paramString2);
        arrayOfString[0] = paramString1.substring(0, i);
        arrayOfString[1] = paramString1.substring(i + paramString2.length());
        return arrayOfString;
    }

    public static boolean copyFile(String paramString1, String paramString2) {
        File localFile1 = new File(paramString1);
        File localFile2 = new File(paramString2);
        FileInputStream localFileInputStream = null;
        FileOutputStream localFileOutputStream = null;
        try {
            localFileInputStream = new FileInputStream(localFile1);
            localFileOutputStream = new FileOutputStream(localFile2);
            byte[] arrayOfByte = new byte[4096];
            int i;
            while ((i = localFileInputStream.read(arrayOfByte)) != -1)
                localFileOutputStream.write(arrayOfByte, 0, i);
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localFileInputStream.close();
        } catch (IOException localIOException) {
            System.out.println(localIOException);
            return false;
        }
        return true;
    }

    public static boolean isExistFile(String paramString) {
        boolean i = false;
        File localFile = new File(paramString);
        if (localFile.isDirectory()) {
            File[] arrayOfFile = localFile.listFiles();
            if ((arrayOfFile != null) && (arrayOfFile.length != 0))
                i = true;
        }
        return i;
    }

    public static File[] getFiles(String paramString) {
        File localFile = new File(paramString);
        return localFile.listFiles();
    }

    public static InputStream getInputStream(String paramString) {
        File localFile = new File(paramString);
        String str1 = getCharset(localFile);
        String str2 = readFile(paramString, str1);
        ByteArrayInputStream localByteArrayInputStream = null;
        try {
            localByteArrayInputStream = new ByteArrayInputStream(
                    str2.getBytes(str1));
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        }
        return localByteArrayInputStream;
    }
}
