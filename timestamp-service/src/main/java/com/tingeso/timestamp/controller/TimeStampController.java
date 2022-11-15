package com.tingeso.timestamp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tingeso.timestamp.entity.Timestamp;
import com.tingeso.timestamp.service.TimeStampService;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/timestamps")
public class TimeStampController {
    @Autowired
    private TimeStampService timestampService;

    @GetMapping
    public ResponseEntity<List<Timestamp>> getAll() {
        List<Timestamp> timestamps = timestampService.getAll();
        if(timestamps.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(timestamps);
    }
    @GetMapping("/byemployee/{rut}/{date}")
    public ResponseEntity<Timestamp> getByEmployee(@PathVariable("rut") String rut, @PathVariable("date") String date) throws ParseException {
        Timestamp timestamps = timestampService.get_dia_trabajado(rut, date);
        if(timestamps == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(timestamps);
    }
    @GetMapping("/date")
    public Date getDate() {
        return timestampService.get_start_date();
    }
    @PostMapping()
    public void saveTxt(@RequestParam("file") MultipartFile file, RedirectAttributes ms) throws FileNotFoundException, ParseException {
        System.out.println("Test");
        timestampService.save(file);
        timestampService.readFile();
    }
}
