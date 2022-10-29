package com.tingeso.marcasreloj.controller;
import com.tingeso.marcasreloj.service.MarcasRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tingeso.marcasreloj.entity.MarcasReloj;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/timestamp")
public class MarcasRelojController {
    @Autowired
    private MarcasRelojService timestampService;

    @GetMapping
    public ResponseEntity<List<MarcasReloj>> getAll() {
        List<MarcasReloj> timestamps = timestampService.getAll();
        if(timestamps.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(timestamps);
    }
    @GetMapping("/hola")
    public String test(){
        return "TEST 2";
    }
    @PostMapping()
    public void saveTxt(@RequestParam("files") MultipartFile file, RedirectAttributes ms) throws FileNotFoundException, ParseException {
        System.out.println("Test");
        timestampService.save(file);
        timestampService.readFile();
    }
}
