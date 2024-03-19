package me.jaketheduque.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class WebController {
    private static final Logger log = Logger.getLogger(WebController.class.getName());
    @PostMapping("/saveimage")
    String saveImage(@RequestBody String base64) throws IOException {
        String base64Data = base64.split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        log.log(Level.INFO, "Received image of size {0}x{1} and {2} bytes!", new Object[]{bufferedImage.getWidth(), bufferedImage.getHeight(), imageBytes.length});

        File image = new File("image.png");
        ImageIO.write(bufferedImage, "png", image);

        return "OKAY";
    }

    @GetMapping(value = "/getimage")
    public ResponseEntity<byte[]> getImage() throws IOException {
        byte[] image = Files.readAllBytes(Path.of("image.png"));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
