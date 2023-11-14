package com.flotte.model.kilometrage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.flotte.security.token.Authentification;
import com.flotte.ws.MyResponse;

import java.util.List;

@RestController
@RequestMapping("api/kilometrages")
public class KilometrageController {

    @Autowired
    Authentification authentification;

    @PostMapping("/save")
    public Kilometrage save(@RequestBody Kilometrage kilometrage) {
        return Kilometrage.save(kilometrage);
    }

    @CrossOrigin
    @GetMapping("/getAllByVehiculeId/{vehiculeId}")
    public MyResponse getKilometragesByVehiculeId(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, 
        @PathVariable Integer vehiculeId) {
        
        MyResponse response = new MyResponse();
        try{
            response.setProfil(authentification.checkValidateToken(authorizationHeader));
            response.setData(Kilometrage.getKilometragesByVehiculeId(vehiculeId));
        }catch(Exception ex){
            if(response.getProfil() == null)response.addError("token", ex.getMessage());
            else response.addError("error", ex.getMessage());
        }
        return response;
    }

    @GetMapping("")
    public List<Kilometrage> getAll() {
        return Kilometrage.getAll();
    }

    @GetMapping("/{id}")
    public Kilometrage getKilometrageById(@PathVariable Integer id) {
        return Kilometrage.getKilometrageById(id);
    }

    @PutMapping("/{id}")
    public Kilometrage updateKilometrage(@PathVariable Integer id, @RequestBody Kilometrage updatedKilometrage) {
        updatedKilometrage.setId(id);
        return Kilometrage.update(updatedKilometrage);
    }

    @DeleteMapping("/{id}")
    public boolean deleteKilometrage(@PathVariable Integer id) {
        return Kilometrage.delete(id);
    }
}