package com.example.carti_web.controller;

import com.example.carti_web.entity.Carte;
import com.example.carti_web.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Main {

    @Autowired
    private CarteRepository carteRepository;


    @GetMapping("/lista-carti")
    public String listaCarti(Model model) {
        model.addAttribute("carti", carteRepository.findAll());
        model.addAttribute("mesaj", "Lista cartilor");
        return "carti";
    }

    @PostMapping("/adauga-carte")
    public String adaugaCarte(Carte carte, Model model) {
        if (carte.getIsbn().isEmpty() || carte.getTitlu().isEmpty() || carte.getAutor().isEmpty()) {
            model.addAttribute("mesaj", "trebuie completet tot");
        } else {
            carteRepository.save(carte);
            model.addAttribute("mesaj", "ok");
        }
        return "redirect:/lista-carti";
    }

    @PostMapping("/filtrare-carti")
    public String filtreazaCarti(String autor, Model model) {
        if (autor.isEmpty()) {
            model.addAttribute("carti", carteRepository.findAll());
            model.addAttribute("mesaj", "carti:");
        } else {
            model.addAttribute("carti", carteRepository.findByAutor(autor));
            model.addAttribute("mesaj", "cartile cu autorul  " + autor + ":");
        }
        return "carti";
    }
}
