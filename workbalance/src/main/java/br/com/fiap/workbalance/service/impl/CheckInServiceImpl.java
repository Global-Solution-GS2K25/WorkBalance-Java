package br.com.fiap.workbalance.service.impl;

import br.com.fiap.workbalance.api.dto.CheckInRequest;
import br.com.fiap.workbalance.api.dto.CheckInResponse;
import br.com.fiap.workbalance.domain.entity.CheckInBemEstar;
import br.com.fiap.workbalance.domain.entity.Usuario;
import br.com.fiap.workbalance.domain.repository.CheckInRepository;
import br.com.fiap.workbalance.domain.repository.UsuarioRepository;
import br.com.fiap.workbalance.service.CheckInService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final UsuarioRepository usuarioRepository;

    public CheckInServiceImpl(CheckInRepository checkInRepository, UsuarioRepository usuarioRepository) {
        this.checkInRepository = checkInRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public CheckInResponse registrarCheckIn(CheckInRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CheckInBemEstar checkin = new CheckInBemEstar();
        checkin.setUsuario(usuario);
        checkin.setDataHora(LocalDateTime.now());
        checkin.setHumor(request.getHumor());
        checkin.setNivelEstresse(request.getNivelEstresse());
        checkin.setQualidadeSono(request.getQualidadeSono());
        checkin.setSintomasFisicos(request.getSintomasFisicos());
        checkin.setObservacoes(request.getObservacoes());

        CheckInBemEstar salvo = checkInRepository.save(checkin);

        return new CheckInResponse(
                salvo.getId(),
                salvo.getUsuario().getId(),
                salvo.getDataHora(),
                salvo.getHumor(),
                salvo.getNivelEstresse(),
                salvo.getQualidadeSono(),
                salvo.getSintomasFisicos(),
                salvo.getObservacoes()
        );
    }

    @Override
    public Page<CheckInResponse> buscarHistorico(Long usuarioId, Pageable pageable) {
        LocalDateTime inicio = LocalDateTime.now().minusMonths(3);
        LocalDateTime fim = LocalDateTime.now();
        Page<CheckInBemEstar> page = checkInRepository
                .findByUsuarioIdAndDataHoraBetween(usuarioId, inicio, fim, pageable);

        return page.map(c -> new CheckInResponse(
                c.getId(),
                c.getUsuario().getId(),
                c.getDataHora(),
                c.getHumor(),
                c.getNivelEstresse(),
                c.getQualidadeSono(),
                c.getSintomasFisicos(),
                c.getObservacoes()
        ));
    }
}
