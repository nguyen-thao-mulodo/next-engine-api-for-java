package com.co.hm.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Defines method related to File handling.
 * <p>
 * - UploadFile: Handle upload file <br>
 * - DownloadCSVFile: Download csv file <br>
 * </p>
 */
public final class FileUtils {

  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

  private FileUtils() {
  }

  /**
   * Upload file
   *
   * @param file A representation of an uploaded file received in a multipart request.
   * @return true if the can save the uploaded file received in a multipart request.
   */
  public static boolean uploadFile(MultipartFile file) {
    if (file.isEmpty()) {
      return false;
    }

    try {
      // Get the file and save it somewhere
      byte[] bytes = file.getBytes();
      Path path = Paths.get(Constant.UPLOADED_FORDER + file.getOriginalFilename());
      Files.write(path, bytes);
    } catch (IOException e) {
      logger.error("error", e);
    }
    return true;
  }

  /**
   * Download file
   *
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client
   * @param directoryPath directory that contains csv files
   * @param fileName the csv file name
   * @return the object of the File need to be download
   */
  public static FileSystemResource downloadCSVFile(HttpServletResponse response, String directoryPath, String fileName) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    return new FileSystemResource(new File(directoryPath + fileName));
  }

}
