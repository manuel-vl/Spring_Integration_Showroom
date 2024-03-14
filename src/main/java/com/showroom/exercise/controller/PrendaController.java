package com.showroom.exercise.controller;

import com.showroom.exercise.dto.PrendaDTO;
import com.showroom.exercise.service.IPrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clothes")
public class PrendaController {
    @Autowired
    IPrendaService prendaService;

    @PostMapping("")
    public ResponseEntity<?> savePrenda(@RequestBody PrendaDTO prendaDTO){
        return new ResponseEntity<>(prendaService.savePrenda(prendaDTO), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getPrendas(){
        return new ResponseEntity<>(prendaService.getPrendas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrendaById(@PathVariable Long id){
        return new ResponseEntity<>(prendaService.getPrendaById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrenda(@PathVariable Long id, @RequestBody PrendaDTO prendaDTO){
        return new ResponseEntity<>(prendaService.updatePrenda(id, prendaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrendaById(@PathVariable Long id){
        return new ResponseEntity<>(prendaService.deletePrenda(id), HttpStatus.OK);
    }
}
