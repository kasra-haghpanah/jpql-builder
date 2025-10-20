package com.jpql.plugin;

//import eu.medsea.mimeutil.MimeUtil2;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Created by kasra.haghpanah on 08/04/2017.
 */
public class FileUtility {


    public String[] getJavaFilesAddressByPackageName(String projectAddress , String[] packages){

        if (packages != null && packages.length > 0) {
            List<String> fileList = new ArrayList<String>();

            for (int i = 0; i < packages.length; i++) {
                String address = projectAddress + "/src/main/java/" + packages[i].replaceAll("\\.", "/");
                FileUtility.getFileList(address, fileList, ".java");
            }

            if (fileList != null && fileList.size() > 0) {
                packages = new String[fileList.size()];
                for (int i = 0; i < fileList.size(); i++) {
                    packages[i] = fileList.get(i);
                }
                return packages;
            }

        }

        return null;
    }

    public static void getFileList(String path, List<String> fileList, String filter) {

        if (fileList == null) {
            fileList = new ArrayList<String>();
        }
        File parent = new File(path);

        if (parent.isDirectory()) {
            for (String pathChild : parent.list()) {
                pathChild = path + "/" + pathChild;
                File child = new File(pathChild);

                if (child.isFile() && (filter == null || filter.equals(""))) {
                    fileList.add(child.getPath());
                } else if (child.isFile() && pathChild.lastIndexOf(filter) == pathChild.length() - filter.length()) {
                    fileList.add(child.getPath());
                } else if (child.isDirectory()) {
                    //System.out.println(pathChild + " -> folder");
                    getFileList(pathChild, fileList, filter);
                }
            }
        } else if (parent.isFile()) {
            System.out.println(parent + " -> file");
        }


    }


    public static String readTextFile(String pathname) {

        int line = -1;
        String text = "";

        FileReader fileReader = null;
        try {
            System.out.println(pathname);
            fileReader = new FileReader(pathname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((line = fileReader.read()) != -1) {
                text += (char)line;
            }
        } catch (IOException e) {


        }
        return text;
    }


    public static String convertStreamToString(InputStream inputStream) {

        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String writeBinaryFile(String pathname, byte[] content) {

        if (content == null) {
            return "no content exist!";
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathname);
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
        try {
            fos.write(content);
            fos.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        return "Uploaded Successfully!";

    }

    public static String writeBinaryFile(String pathname, InputStream content) {

        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(content);
        } catch (IOException e) {
            return e.getMessage();
        }
        return writeBinaryFile(pathname, bytes);
    }

    public static void writeTextFile(String pathname, String content) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(pathname);
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public static byte[] serialize(Object obj) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) {

        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMimeType(String path) {

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        if (file.isDirectory()) {
            return "directory";
        }

        Path pathObj = Paths.get(path);
        String mime = null;
        try {
            mime = Files.probeContentType(pathObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        MimeUtil2 mimeUtil = new MimeUtil2();
//        mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
//        String mimeType = MimeUtil2.getMostSpecificMimeType(mimeUtil.getMimeTypes(path)).toString();
        return mime;
    }

    private static String getFullPath(String dir, String dirnmame) {

        dir = StringEscapeUtils.unescapeJavaScript(dir);
        dirnmame = StringEscapeUtils.unescapeJavaScript(dirnmame);
        dir = dir + "/" + dirnmame;
        dir = dir.replaceAll("(/)+", "/");
        return dir;
    }

    public static void deleteDirectory(String path) {

        File file = new File(path);
        if (file.isFile()) {
            file.delete();
            return;
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                file.delete();
                return;
            }
            for (File f : files) {
                deleteDirectory(f.getAbsolutePath());
            }
            deleteDirectory(file.getAbsolutePath());
        }

    }

    public static void createDirectory(String path) {

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void moveDirectory(String oldPath, String newPath) {
        File file1 = new File(oldPath);
        newPath = formatPathFile(oldPath, newPath);
        File file2 = new File(newPath);
        String path1 = file1.getAbsolutePath();
        String path2 = file2.getAbsolutePath();
        if (path2.indexOf(path1) > -1 || path1.indexOf(path2) > -1) {
            return;
        }

        if (file1.exists()) {
            if (file1.isFile()) {
                file1.renameTo(file2);
            } else if (file1.isDirectory()) {
                moveDir(oldPath, newPath);
            }
        }
    }

    private static void moveDir(String oldPath, String newPath) {

        File file = new File(oldPath);
        if (file.isDirectory()) {
            //create new directory in newPath
            //
            File[] files = file.listFiles();
            file.renameTo(new File(newPath));

            for (File f : files) {
                moveDir(file.getAbsolutePath(), newPath + "/" + file.getName());
            }
        } else if (file.isFile()) {
            file.renameTo(new File(newPath + "/" + file.getName()));
        }

    }

    public static String formatPathFile(String path1, String path2) {

        int s1LastDot = path1.lastIndexOf(".");

        String format1 = "";

        if (s1LastDot > -1 && path1.lastIndexOf("/") < s1LastDot) {
            format1 = path1.substring(s1LastDot, path1.length());
        }

        int existFormat = path2.lastIndexOf(format1);

        if (existFormat < 0) {
            path2 += format1;
        } else if (existFormat != path2.lastIndexOf(format1)) {
            path2 += format1;
        }

        return path2;
    }

    public static void zip(String zipFilename, String... sources) {

        Map<String, String> fileList = new HashMap<String, String>();
        for (String source : sources) {
            File sourceFile = new File(source);
            if (sourceFile.exists()) {
                String root = sourceFile.getParent().toString();
                getFileListForZip(root, source, fileList);
            }
        }
        System.out.println(fileList);

        if (fileList.isEmpty()) {
            return;
        }

        try {

            FileOutputStream fos = new FileOutputStream(zipFilename);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFilename);

            for (String key : fileList.keySet()) {

                String file = fileList.get(key);
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);
                FileInputStream inputStream = new FileInputStream(key);

                IOUtils.copy(inputStream, zos);
                zos.flush();
                inputStream.close();
            }

            zos.closeEntry();
            //remember close it
            zos.close();

            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void getFileListForZip(String root, String source, Map<String, String> fileList) {

        if (fileList == null) {
            fileList = new HashMap<String, String>();
        }
        File node = new File(source);
        //add file only
        if (node.isFile()) {

            String absoluteFile = node.getAbsoluteFile().toString();
            fileList.put(absoluteFile, absoluteFile.substring(root.length() + 1, absoluteFile.length()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                getFileListForZip(root, node.getAbsolutePath() + File.separator + filename, fileList);
            }
        }

    }

    public static void unzip(String outputFolder, String... zipFiles) {

        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            for (String zipFile : zipFiles) {
                //get the zip file content
                if (new File(zipFile).exists() && getMimeType(zipFile).toLowerCase().indexOf("zip") > -1) {
                    ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
                    //get the zipped file list entry
                    ZipEntry ze = zis.getNextEntry();

                    while (ze != null) {

                        String fileName = ze.getName();
                        File newFile = new File(outputFolder + File.separator + fileName);

                        System.out.println("file unzip : " + newFile.getAbsoluteFile());

                        //create all non exists folders
                        //else you will hit FileNotFoundException for compressed folder
                        if (ze.isDirectory()) {
                            newFile.mkdirs();
                        } else {

                            new File(newFile.getParent()).mkdirs();
                            FileOutputStream fos = new FileOutputStream(newFile);
                            fos.write(IOUtils.toByteArray(zis));
                            fos.close();
                        }
                        ze = zis.getNextEntry();
                    }

                    zis.closeEntry();
                    zis.close();

                    System.out.println("Done");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
