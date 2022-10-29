package com.tingeso.marcasreloj.controller;
import com.tingeso.marcasreloj.service.MarcasRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.FileNotFoundException;
import java.text.ParseException;

@RestController
@RequestMapping("/timestamp")
public class MarcasRelojController {
    @Autowired
    private MarcasRelojService upload;
    @GetMapping("/hola")
    public String test(){
        return "TEST";
    }
    @PostMapping()
    public void saveTxt( @RequestParam("archivos") MultipartFile file, RedirectAttributes ms) throws FileNotFoundException, ParseException {
        System.out.println("Test");
        upload.save(file);
    }
}
