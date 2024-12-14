package org.project.smumap.Tile.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.smumap.Tile.dto.TileInfoRequest;
import org.project.smumap.Tile.service.TileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RequiredArgsConstructor
@RestController
public class TileApiController {

    private final TileService tileService;

    @PostMapping(value = "/uploadTile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> getMapTile(@RequestPart("tiles") MultipartFile tile) throws BadRequestException {
        if (tile.isEmpty() || !tile.getOriginalFilename().endsWith(".zip")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only zip file upload");
        }

        String userHomeDir = System.getProperty("user.home");
        Path savePath = Paths.get(userHomeDir, "uploads", "saveImg").toAbsolutePath();
        File saveDir = savePath.toFile();

        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(tile.getInputStream())) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && isImageFile(entry.getName())) {
                    saveImage(zipInputStream, entry.getName(), savePath);
                }
                zipInputStream.closeEntry();
            }
            return ResponseEntity.ok("Images extracted and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    // 파일 이미지 확인
    private boolean isImageFile(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        return lowerCaseName.endsWith(".jpg") || lowerCaseName.endsWith(".jpeg") || lowerCaseName.endsWith(".png");
    }

    // 이미지 저장
    private void saveImage(InputStream inputStream, String fileName, Path uploadDir) throws IOException {
        // Ensure parent directories exist
        File imageFile = new File(uploadDir.toFile(), fileName);
        File parentDir = imageFile.getParentFile();
        if (!parentDir.exists()) {
            System.out.println("Creating directories: " + parentDir.getAbsolutePath());
            parentDir.mkdirs();
        }

        System.out.println("Saving file to user's home directory: " + imageFile.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // 타일 경로와 이름 저장
        TileInfoRequest dto = TileInfoRequest.builder()
                .tilePath("/uploads/" + fileName)
                .tileName(imageFile.getName())
                .build();

        tileService.save(dto);
        System.out.println("File saved successfully to user's home directory.");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/tiles")
    public ResponseEntity<List<String>> getMap() {
        return ResponseEntity.status(HttpStatus.OK).body(tileService.findAllTiles());
    }
}