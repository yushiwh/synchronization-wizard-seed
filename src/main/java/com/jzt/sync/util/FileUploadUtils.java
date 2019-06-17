package com.jzt.sync.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    public FileUploadUtils() {
    }

    public static String uploadFile(MultipartFile multipartFile, String absolutePath, String relativePath, long maxSize, String[] includesuffixs) throws Exception {
        String realAbsolutePath = initPath(absolutePath + relativePath);
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return null;
        } else {
            String[] split = fileName.split("\\.");
            String suffix = split.length >= 2 ? split[split.length - 1] : "temp";
            canUpload(multipartFile, suffix, maxSize, includesuffixs);
            String filePrefix = (new Long(System.currentTimeMillis())).toString() + UUID.randomUUID().toString().substring(0, 3);
            String destFileName = realAbsolutePath + "/" + filePrefix + "." + suffix;
            File destFile = new File(destFileName);
            InputStream stream = null;

            Object var15;
            try {
                stream = multipartFile.getInputStream();
                saveFileFromInputStream(stream, destFile);
                logger.info("上传文件:" + destFileName);
                return destFileName.replace(absolutePath, "");
            } catch (Exception var19) {
                logger.error("上传文件出错", var19);
                var15 = null;
            } finally {
                if (stream != null) {
                    stream.close();
                }

            }

            return (String)var15;
        }
    }

    public static byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return out.toByteArray();
    }

    public static String uploadByteFile(byte[] bytes, String absolutePath, String relativePath, long maxSize, String[] includesuffixs) {
        String realAbsolutePath = initPath(absolutePath + relativePath);
        String files = (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date()) + ((new Random()).nextInt(9000) % 8001 + 1000) + ".png";
        String filename = realAbsolutePath + "/" + files;
        FileOutputStream imageStream = null;

        Object var11;
        try {
            File imageFile = new File(filename);
            imageFile.createNewFile();
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }

            imageStream = new FileOutputStream(imageFile);
            imageStream.write(bytes);
            imageStream.flush();
            imageStream.close();
            return filename.replace(absolutePath, "");
        } catch (Exception var15) {
            var15.printStackTrace();
            var11 = null;
        } finally {
            IOUtils.closeQuietly(imageStream);
        }

        return (String)var11;
    }

    public static String uploadByteFileSmall(String absolutePath, String picUrl) throws Exception {
        String[] split = picUrl.split("\\.");
        String suffix = split[0] + "_s." + split[1];
        String smallFileName = absolutePath + suffix;
        FileInputStream fis = null;

        try {
            File file = new File(absolutePath + picUrl);
            if (!file.exists()) {
                throw new RuntimeException("生成缩略图失败");
            }

            fis = new FileInputStream(file);
            Thumbnails.of(new InputStream[]{fis}).size(180, 180).toFile(smallFileName);
            logger.info("上传文件:" + smallFileName);
        } catch (Exception var10) {
            logger.error("上传文件出错", var10);
        } finally {
            IOUtils.closeQuietly(fis);
        }

        return smallFileName.replace(absolutePath, "");
    }

    public static String uploadFileSmall(MultipartFile multipartFile, String absolutePath, String picUrl) throws Exception {
        String[] split = picUrl.split("\\.");
        String suffix = split[0] + "_s." + split[1];
        String smallFileName = absolutePath + suffix;

        try {
            Thumbnails.of(new InputStream[]{ multipartFile.getInputStream()}).size(180, 180).toFile(smallFileName);
            logger.info("上传文件:" + smallFileName);
        } catch (Exception var7) {
            logger.error("上传文件出错", var7);
        }

        return smallFileName.replace(absolutePath, "");
    }

    private static void saveFileFromInputStream(InputStream stream, File file) throws Exception {
        FileOutputStream fs = null;

        try {
            fs = new FileOutputStream(file);
            byte[] buffer = new byte[1048576];
            boolean var4 = false;

            int byteread;
            while((byteread = stream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
        } catch (Exception var8) {
            throw var8;
        } finally {
            if (fs != null) {
                fs.close();
            }

        }

    }

    private static String initPathNoDay(String absolutePath) {
        String todayPath = absolutePath.endsWith("/") ? absolutePath : absolutePath + "/";
        initDir(todayPath);
        return todayPath;
    }

    private static String initPath(String absolutePath) {
        String today = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String todayPath = absolutePath.endsWith("/") ? absolutePath + today : absolutePath + "/" + today;
        initDir(todayPath);
        return todayPath;
    }

    private static void initDir(String uploadPath) {
        File directory = new File(uploadPath);
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

    }

    private static void canUpload(MultipartFile multipartFile, String suffix, long maxSize, String[] includesuffixs) throws IOException {
        if (multipartFile != null && multipartFile.getSize() != 0L) {
            if (multipartFile.getSize() > maxSize * 1024L) {
                throw new IOException("上传文件不能超过  " + maxSize + " K");
            } else if (includesuffixs != null && includesuffixs.length != 0) {
                boolean eixstSuffix = false;
                String[] var6 = includesuffixs;
                int var7 = includesuffixs.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String includesuffix = var6[var8];
                    if (includesuffix.toLowerCase().equals(suffix.toLowerCase())) {
                        eixstSuffix = true;
                        break;
                    }
                }

                if (!eixstSuffix) {
                    throw new IOException("上传的文件格式不正确");
                }
            }
        } else {
            throw new IOException("上传文件不存在");
        }
    }

    public static String checkPictureFormatAndReturnPath(MultipartFile pictureFile, String upload_absolute_path, String upload_relative_path) throws Exception {
        String pictureName = pictureFile.getOriginalFilename();
        if (null != pictureName && !pictureName.equals("")) {
            if (!pictureName.toLowerCase().endsWith(".png") && !pictureName.toLowerCase().endsWith(".jpg")) {
                throw new Exception("文件上传格式不正确!");
            }

            if (null != pictureFile && !pictureFile.isEmpty()) {
                return uploadFile(pictureFile, upload_absolute_path, upload_relative_path, 10240L, new String[]{"jpg", "png", "bmp", "gif"});
            }
        }

        return null;
    }

    public static Map upLoadMainAndThumbPicture(MultipartFile multipartFile, String absolutePath, String relativePath, long maxSize, String[] includesuffixs) throws Exception {
        String mainUrl = uploadFile(multipartFile, absolutePath, relativePath, maxSize, includesuffixs);
        String smallUrl = uploadFileSmall(multipartFile, absolutePath, mainUrl);
        Map resulrMap = new HashMap();
        resulrMap.put("mainUrl", mainUrl);
        resulrMap.put("smallUrl", smallUrl);
        return resulrMap;
    }

    public static Map upLoadMainAndThumbPictureForBase64(byte[] bytes, String absolutePath, String relativePath, long maxSize, String[] includesuffixs, boolean fileFlag) throws Exception {
        String smallUrl = "";
        String mainUrl = uploadByteFile(bytes, absolutePath, relativePath, maxSize, includesuffixs);
        if (fileFlag) {
            smallUrl = uploadByteFileSmall(absolutePath, mainUrl);
        }

        Map resulrMap = new HashMap();
        resulrMap.put("mainUrl", mainUrl);
        resulrMap.put("smallUrl", smallUrl);
        return resulrMap;
    }
}
