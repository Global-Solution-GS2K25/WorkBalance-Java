package br.com.fiap.workbalance.domain.repository;

import br.com.fiap.workbalance.domain.entity.CheckInBemEstar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CheckInRepository extends JpaRepository<CheckInBemEstar, Long> {

    Page<CheckInBemEstar> findByUsuarioIdAndDataHoraBetween(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim,
            Pageable pageable
    );
}
