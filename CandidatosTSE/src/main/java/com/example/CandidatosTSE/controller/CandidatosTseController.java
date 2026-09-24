package com.example.CandidatosTSE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CandidatosTSE.service.CandidatosTseService;

@Controller
@RequestMapping("/candidatos")
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model) {

        model.addAttribute(
                "candidatos",
                candidatosTseService.filtrar(cargo, partido, texto)
        );

        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());

        model.addAttribute("cargoSelecionado", cargo);
        model.addAttribute("partidoSelecionado", partido);
        model.addAttribute("texto", texto);

        return "candidatos";
    }
}
