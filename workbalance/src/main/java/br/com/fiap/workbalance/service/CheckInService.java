package br.com.fiap.workbalance.service;

import br.com.fiap.workbalance.api.dto.CheckInRequest;
import br.com.fiap.workbalance.api.dto.CheckInResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckInService {

    CheckInResponse registrarCheckIn(CheckInRequest request);

    Page<CheckInResponse> buscarHistorico(Long usuarioId, Pageable pageable);
}
