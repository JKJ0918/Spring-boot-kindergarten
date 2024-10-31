package com.kinder.kindergarten.service;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log4j2
public class FileService {

  @Value("${uploadPath1}")
  private String uploadPath;

  public String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
    log.info("파일 업로드 - FileService.uploadFile()실행 ");
    Ulid ulid = UlidCreator.getUlid();
    String extension = originalName.substring(originalName.lastIndexOf("."));
    String modifiedName = ulid.toString() + extension;
    String filePath = uploadPath + "/" + modifiedName;
    //실제로 파일경로에 저장
    FileOutputStream fos = new FileOutputStream(filePath);
    fos.write(fileData);
    fos.close();

    return modifiedName;
  }

  public void deleteFile(String filePath) throws Exception {
    File deleteFile = new File(filePath);

    if(deleteFile.exists()) {
      deleteFile.delete();
      log.info("파일을 삭제하였습니다.");
    } else {
      log.info("파일이 존재하지 않습니다.");
    }
  }


  public String getFullPath(String filename) {
    return uploadPath + "/" + filename;
  }
//경로 가져오기

}
