package com.flotte.model.vehicule;

import org.springframework.web.bind.annotation.*;

import com.flotte.ws.MyResponse;

@RestController
@RequestMapping("api/vehicules")
public class VehiculeController {

    @PostMapping("")
    public Vehicule save(@RequestBody Vehicule vehicule){
        try {
            return Vehicule.save(vehicule);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MyResponse getVehiculeById(@PathVariable Integer id) {
        MyResponse response = new MyResponse();
        try{
            response.setData(Vehicule.getVehiculeById(id));
        }catch(Exception ex){
            response.addError("Error 1", ex.getMessage());
        }
        return response;
    }

    @CrossOrigin
    @GetMapping("")
    public MyResponse getAll(){
        MyResponse response = new MyResponse();
        try{
            response.setData(Vehicule.getAll());
        }catch(Exception ex){
            response.addError("Error 1", ex.getMessage());
        }
        return response;
    }

    @PutMapping("/{id}")
    public Vehicule updateVehicule(@PathVariable Integer id, @RequestBody Vehicule updatedVehicule) {
        updatedVehicule.setId(id);
        return Vehicule.update(updatedVehicule);
    }

    @DeleteMapping("/{id}")
    public boolean deleteVehicule(@PathVariable Integer id) {
        return Vehicule.delete(id);
    }
}
