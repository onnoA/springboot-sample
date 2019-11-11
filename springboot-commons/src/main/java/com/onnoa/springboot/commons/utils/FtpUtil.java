package com.onnoa.springboot.commons.utils;

import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @Description: 文件工具类
 * @Author: onnoA
 * @Date: 2019/11/11 16:26
 */
public class FtpUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /*protected static String ftpUrl = PlaceholderHolder.getProperty("FTP_ADDRESS");
    protected static String userName= PlaceholderHolder.getProperty("FTP_USERNAME");
    protected static int port = Integer.parseInt(PlaceholderHolder.getProperty("FTP_PORT"));
    protected static String password= PlaceholderHolder.getProperty("FTP_PASSWORD");
    protected static String directory= PlaceholderHolder.getProperty("FTP_FILEPATH");*/

    /**
     * 创建临时文件
     * @param sourceString
     * @return
     */
    public static File CreateTemporaryFile(String sourceString,String fileName,String suffix) {
        try {
            File tempFile = File.createTempFile(fileName, suffix);
            logger.info("正在生成文件,当前环境编码为:" + Charset.defaultCharset());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));
            writer.write(sourceString);
            writer.close(); // 关闭文件输出流
            return tempFile;
        } catch (Exception e) {
            logger.error("生成临时文件失败：" + fileName + "." + suffix, e);
        }
        return null;
    }

    /**
     * 删除临时文件
     * @param file
     */
    public static void DeleteTemporaryFile(File file) {
        if (file != null) {
            file.deleteOnExit();
        }
    }

    /**
     * NIO 读取文件 效率高
     * @author zhangshujian
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  递归删除目录下的子目录及所有文件
     *  @author zhangshujian
     *
     * @param dir 将要删除的目录
     * @return
     */
    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
