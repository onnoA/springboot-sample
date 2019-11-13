package com.onnoa.springboot.commons.utils;

import ch.qos.logback.core.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


/**
 * @Description: 文件工具类
 * @Author: onnoA
 * @Date: 2019/11/11 16:26
 */
public class FtpUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    protected static String ftpUrl = PropertiesUtil.getProperty("FTP_ADDRESS");
    protected static String userName= PropertiesUtil.getProperty("FTP_USERNAME");
    protected static int port = Integer.parseInt(PropertiesUtil.getProperty("FTP_PORT"));
    protected static String password= PropertiesUtil.getProperty("FTP_PASSWORD");
    protected static String directory= PropertiesUtil.getProperty("FTP_FILEPATH");

    private static FTPClient getFtpClient(String directory) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ftpUrl,port);
        boolean loginSuccess = ftpClient.login(userName, password);
        logger.info("登录ftp服务:{}，用户:{},密码:{},结果:{}",ftpUrl,userName,password,loginSuccess);
        ftpClient.enterLocalPassiveMode();
        List<String> directorys = getDirectories(directory);
        if (directorys != null) {
            for (String dir : directorys) {
                if (!isExitsDir(ftpClient, dir)) {
                    ftpClient.makeDirectory(dir);

                }
                ftpClient.changeWorkingDirectory(dir);
            }
        }

        ftpClient.setBufferSize(1024);
        ftpClient.setControlEncoding("UTF-8");
        // 设置文件类型（二进制）
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        return ftpClient;
    }

    private static List<String> getDirectories(String directory) {
        if (StringUtils.isBlank(directory)) {
            return null;
        }
        String[] dis = directory.split("/");
        return Arrays.asList(dis);
    }

    /**
     * ftp上传单个文件
     * @param destName  上传至ftp后存储的文件名
     * @throws IOException
     */
    public static boolean upload(String directory, InputStream input, String destName) throws IOException {
        try {
            FTPClient ftpClient = getFtpClient(directory);

            boolean result = ftpClient.storeFile(destName, input);

            logger.info("ftpClient.getReplyString():"+ftpClient.getReplyString());

            ftpClient.disconnect();
            return result;
        } catch(Exception e) {
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * FTP单个文件下载
     * @param destFileName            要下载的文件名
     * @param downloadName            下载后锁存储的文件名全路径
     */
    public static boolean download(String directory,String destFileName,String downloadName)throws IOException {
        try {
            FTPClient ftpClient = getFtpClient(directory);
            boolean result = ftpClient.retrieveFile(destFileName, new FileOutputStream(downloadName));
            ftpClient.disconnect();
            return result;
        } catch(Exception e) {
            logger.error("文件下载失败!",e);
            throw e;
        }
    }

    /**
     *
     * @param oldFileName              要重命名的文件名
     * @param newFileName              重命名后的文件名
     * @throws IOException
     */
    public static boolean rename(String directory,String oldFileName, String newFileName) throws IOException {
        try {
            FTPClient ftpClient = getFtpClient(directory);
            boolean result = ftpClient.rename(oldFileName, newFileName);//重命名远程文件
            ftpClient.disconnect();
            return result;
        } catch(NumberFormatException e){
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        }
    }

    /**
     *  判断远程文件是否移除成功，如果成功返回true，否则返回false
     * @param fileName                        要删除的文件名
     * @return
     * @throws IOException
     */
    public static boolean remove(String directory,String fileName) throws IOException{
        try {
            FTPClient ftpClient = getFtpClient(directory);
            boolean result = ftpClient.deleteFile(fileName);//删除远程文件
            ftpClient.disconnect();
            return result;
        } catch(Exception e) {
            throw new IOException("连接ftp服务器失败！", e);
        }
    }

    /**
     * @param newDirectory        要创建的新目录名
     */
    public static boolean makeDirecotory(String directory,String newDirectory) throws IOException{
        try {
            FTPClient ftpClient = getFtpClient(directory);
            boolean result = ftpClient.makeDirectory(newDirectory);//创建新目录
            ftpClient.disconnect();
            return result;
        } catch(NumberFormatException e){
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        }
    }

    /**
     * @param directoryName        要查找的目录名
     */
    public static boolean isExitsDirectory(String directory,String directoryName) throws IOException{
        try {
            FTPClient ftpClient = getFtpClient(directory);
            boolean exitsDir = isExitsDir(ftpClient, directoryName);
            ftpClient.disconnect();
            return exitsDir;
        } catch(NumberFormatException e){
            throw e;
        } catch (IOException e) {
            throw new IOException("连接ftp服务器失败！", e);
        }
    }

    private static boolean isExitsDir(FTPClient ftpClient, String directoryName) throws IOException {
        FTPFile[] ftpFiles = ftpClient.listDirectories();
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.isDirectory()&&ftpFile.getName().equals(directoryName)) {
                return true;
            }
        }
        return false;
    }

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
