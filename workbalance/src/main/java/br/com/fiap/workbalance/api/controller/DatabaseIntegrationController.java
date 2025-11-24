package br.com.fiap.workbalance.api.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.workbalance.api.dto.CheckInRequest;
import br.com.fiap.workbalance.service.DatabaseIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/db")
public class DatabaseIntegrationController {

    private final DatabaseIntegrationService dbService;

    public DatabaseIntegrationController(DatabaseIntegrationService dbService) {
        this.dbService = dbService;
    }

    @PostMapping("/checkins/procedure")
    @Operation(summary = "Registrar check-in via procedure PR_CHECKIN_INS")
    public ResponseEntity<?> registrarCheckinViaProcedure(
            @RequestBody @Valid CheckInRequest request) {

        boolean pode = dbService.podeFazerCheckinHoje(request.getUsuarioId());
        if (!pode) {
            return ResponseEntity.badRequest()
                    .body("Usuário já realizou check-in hoje (fn_pode_fazer_checkin)");
        }

        Long idGerado = dbService.registrarCheckInViaProcedure(request);
        return ResponseEntity.ok("Check-in criado via procedure com ID = " + idGerado);
    }

    @GetMapping("/equipes/{idEquipe}/media-humor")
    @Operation(summary = "Média de humor da equipe no intervalo informado")
    public ResponseEntity<?> mediaHumorEquipe(
            @PathVariable Long idEquipe,
            @RequestParam("dataInicio")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicio,
            @RequestParam("dataFim")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFim) {

        Double media = dbService.calcularMediaHumorEquipe(idEquipe, dataInicio, dataFim);
        return ResponseEntity.ok(media);
    }

    @GetMapping("/equipes/{idEquipe}/indice-risco")
    @Operation(summary = "Índice de risco da equipe (BAIXO/MEDIO/ALTO)")
    public ResponseEntity<?> indiceRiscoEquipe(@PathVariable Long idEquipe) {
        String risco = dbService.obterIndiceRiscoEquipe(idEquipe);
        return ResponseEntity.ok(risco);
    }
}
