package br.com.fiap.workbalance.api.controller;

import br.com.fiap.workbalance.api.dto.CheckInRequest;
import br.com.fiap.workbalance.api.dto.CheckInResponse;
import br.com.fiap.workbalance.service.CheckInService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping
    public ResponseEntity<CheckInResponse> registrar(@RequestBody @Valid CheckInRequest request) {
        CheckInResponse response = checkInService.registrarCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public Page<CheckInResponse> historico(@RequestParam Long usuarioId, Pageable pageable) {
        return checkInService.buscarHistorico(usuarioId, pageable);
    }
}
