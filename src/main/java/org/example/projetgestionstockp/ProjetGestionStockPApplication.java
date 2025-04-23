package org.example.projetgestionstockp;

import org.example.projetgestionstockp.Service.FournisseurService;
import org.example.projetgestionstockp.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetGestionStockPApplication   {
    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
   private  PersonneService personneService ;
    public static void main(String[] args) {
        SpringApplication.run(ProjetGestionStockPApplication.class, args);
    }






}
