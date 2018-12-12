package com.isa.usersengine.cdi;

import com.isa.usersengine.exceptions.UserImageNotFound;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@ApplicationScoped
public class FileUploadProcessorDaoBean implements FileUploadProcessorDao {

    private static final String SETTING_FILE = "settings.properties";
    private static final String SETTING_KEY = "Upload.Path.Images";

    @Override
    public String getUploadImageFilesPath() throws IOException {
        Properties settings = new Properties();
        settings.load(Thread.currentThread()
                .getContextClassLoader().getResource(SETTING_FILE).openStream());
        return settings.getProperty(SETTING_KEY);
    }

    public File uploadImageFile(Part filePart) throws IOException, UserImageNotFound {

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (fileName == null || fileName.isEmpty()) {
            throw new UserImageNotFound("No image uploaded");
        }

        File file = new File(getUploadImageFilesPath() + fileName);

        Files.deleteIfExists(file.toPath());

        InputStream fileContent = filePart.getInputStream();
//        OutputStream os = new FileOutputStream(file);
//
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        while ((bytesRead = fileContent.read(buffer)) != -1) {
//            os.write(buffer, 0, bytesRead);
//        }

        Files.copy(fileContent, file.toPath());

        fileContent.close();

//        os.flush();
//        os.close();

        return file;
    }
}
