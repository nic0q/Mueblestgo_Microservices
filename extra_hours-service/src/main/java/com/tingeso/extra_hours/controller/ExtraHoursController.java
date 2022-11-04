package com.tingeso.extra_hours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingeso.extra_hours.service.ExtraHoursService;

@RestController
@RequestMapping("/extra-hours")
public class ExtraHoursController {
  @Autowired
  ExtraHoursService justificativeService;

}
