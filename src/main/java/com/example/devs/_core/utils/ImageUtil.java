package com.example.devs._core.utils;

import com.example.devs.model.board.BoardRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ImageUtil {

    private static final String UPLOAD_DIR = "./upload/";

    // 파일 업로드 결과를 저장하는 클래스, 파일 이름과 파일 경로를 포함합니다.
    public static class FileUploadResult {
        private String fileName; // 업로드된 파일의 이름
        private String filePath; // 파일이 저장된 경로

        // 파일 이름과 경로를 초기화하는 생성자
        public FileUploadResult(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath.substring(1); // 경로의 첫 문자를 제거합니다.
        }

        // fileName의 getter 메서드
        public String getFileName() {
            return fileName;
        }

        // filePath의 getter 메서드
        public String getFilePath() {
            return filePath;
        }
    }

    // 이미지 파일인지 확인하는 메서드
    private static boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null &&
                (contentType.startsWith("image/"));
    }

    // 하나의 파일을 업로드하는 메서드
    public static FileUploadResult uploadFile(MultipartFile file) {
        String contentType = file.getContentType();
        System.out.println("ContentType: " + contentType); // ContentType 출력 (디버깅 용)

        // 파일이 이미지이 아닌 경우 예외를 던짐
        if (!isImageFile(file)) {
            throw new IllegalArgumentException("이미지 또는 영상 파일만 업로드할 수 있습니다.");
        }

        // UUID를 사용하여 고유한 파일 이름 생성
        String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename() + fileExtension;

        // 지정된 디렉토리에 파일 경로 생성
        Path filePath = Paths.get("./upload", fileName);

        try {
            // 업로드 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(filePath.getParent());
            // 지정된 경로에 파일 작성
            Files.write(filePath, file.getBytes());

        } catch (Exception e) {
            // 오류가 발생하면 스택 트레이스를 출력하고 런타임 예외를 던짐
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류 발생: " + e.getMessage());
        }

        // 파일 업로드 결과를 반환
        return new FileUploadResult(fileName, filePath.toString());
    }

    // 여러 파일을 업로드하는 메서드
    public static List<FileUploadResult> uploadFiles(List<MultipartFile> files) {
        // 각 파일 업로드 결과를 저장할 리스트
        List<FileUploadResult> uploadResults = new ArrayList<>();
        // 각 파일을 순회하면서 업로드
        for (MultipartFile file : files) {
            uploadResults.add(uploadFile(file));
        }
        // 업로드 결과 리스트를 반환
        return uploadResults;
    }

    public static List<FileUploadResult> uploadBase64Images(List<BoardRequest.Base64Image> base64Images) throws IOException {
        List<FileUploadResult> uploadResults = new ArrayList<>();
        // 1. JSON으로 전달된 이미지를 순회하며 파일로 저장,
        // 2. Photo_tb에 저장
        if (base64Images != null) {
            for (BoardRequest.Base64Image image : base64Images) {
                //1
                byte[] imageBytes;
                try {
                    imageBytes = Base64.getDecoder().decode(image.getImageData());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid Base64 data for image: " + image.getFileName(), e);
                }

                String originalFileName = image.getFileName();
                String fileExtension = FileUtil.getFileExtension(originalFileName);
                String uuidFileName = UUID.randomUUID().toString() + fileExtension;

                // 저장할 디렉토리 경로 설정
                String directoryPath = "./upload/";
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs(); // 디렉토리가 존재하지 않으면 생성
                }

                String filePath = directoryPath + uuidFileName;

                try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                    fos.write(imageBytes);
                } catch (IOException e) {
                    throw new IOException("Failed to save image: " + originalFileName, e);
                }
                String filePath2 = "/upload/" + uuidFileName;
                uploadResults.add( new FileUploadResult(uuidFileName, filePath2) );
            }
            for (FileUploadResult result : uploadResults) {
                System.out.println("result.getFileName() = " + result.getFileName());
                System.out.println("result.getFilePath() = " + result.getFilePath());
            }
            return uploadResults;
        }
        return null;
    }

    // 링크 이미지 다운로드
    public static String downloadImage(String imageUrl) {
        // 이미지 URL에서 파일 확장자 추출
        String fileExtension = imageUrl.substring(imageUrl.lastIndexOf("."));
        // UUID로 고유한 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + fileExtension;
        String destinationFilePath = UPLOAD_DIR + fileName;

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            Path savePath = Paths.get(destinationFilePath);
            Files.createDirectories(savePath.getParent());

            try (FileOutputStream outputStream = new FileOutputStream(savePath.toFile())) {
                IOUtils.copy(inputStream, outputStream);
            }

            inputStream.close();
            connection.disconnect();

            System.out.println("Image downloaded successfully: " + destinationFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to download image: " + e.getMessage());
            return null;
        }
        return "/upload/" + fileName;
    }

    // byte[] 배열과 파일 이름을 받아 파일을 업로드하는 메서드
    public static FileUploadResult uploadFile(byte[] fileData, String originalFileName) {
        // 파일이 이미지이 아닌 경우 예외를 던짐
        if (!isImageFile(originalFileName)) {
            throw new IllegalArgumentException("이미지 또는 영상 파일만 업로드할 수 있습니다.");
        }

        // UUID를 사용하여 고유한 파일 이름 생성
        String fileExtension = FileUtil.getFileExtension(originalFileName);
        String fileName = UUID.randomUUID() + "_" + originalFileName + fileExtension;

        // 지정된 디렉토리에 파일 경로 생성
        Path filePath = Paths.get("./upload", fileName);

        try {
            // 업로드 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(filePath.getParent());
            // 지정된 경로에 파일 작성
            Files.write(filePath, fileData);

        } catch (Exception e) {
            // 오류가 발생하면 스택 트레이스를 출력하고 런타임 예외를 던짐
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류 발생: " + e.getMessage());
        }

        // 파일 업로드 결과를 반환
        return new FileUploadResult(fileName, filePath.toString());
    }

    // 파일 이름으로 파일이 이미지인지 확인하는 메서드 (확장자로 확인)
    private static boolean isImageFile(String fileName) {
        String fileExtension = FileUtil.getFileExtension(fileName).toLowerCase();
        return fileExtension.equals(".jpg") || fileExtension.equals(".jpeg") || fileExtension.equals(".png") || fileExtension.equals(".gif");
    }
}