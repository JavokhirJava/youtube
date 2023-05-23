package company.controller;

import company.dto.attach.AttachDTO;
import company.service.AttachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/public/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        log.info("File upload" + file.getOriginalFilename());
        String fileName = attachService.saveToSystem3(file);
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping(value = "/public/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        log.info("open file " + fileName);
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }

    @GetMapping(value = "/public/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        // ahshdasd.jpg
        log.info("{open attach}" + fileName);
        return attachService.open_general(fileName);
    }

    @GetMapping("/public/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        Resource file = attachService.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PutMapping(value = "/adm/paging")
    public ResponseEntity<Page<AttachDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size) {
        Page<AttachDTO> response = attachService.pagingtion(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/adm/delete/{fineName}")
    public ResponseEntity<Boolean> delete(@PathVariable("fineName") String fileName) {
        return ResponseEntity.ok(attachService.delete(fileName));
    }

}