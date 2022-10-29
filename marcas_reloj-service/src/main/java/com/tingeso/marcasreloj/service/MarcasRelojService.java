package com.tingeso.marcasreloj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MarcasRelojService {
        private final Logger logg = LoggerFactory.getLogger(MarcasRelojService.class);
        public String save(MultipartFile file) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                } catch (IOException e) {
                    logg.error("No se pudo guardar", e);
                }
            }
            return "Archivo guardado correctamente";
        }
}
