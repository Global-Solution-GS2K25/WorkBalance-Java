package br.com.fiap.workbalance.domain.repository;

import br.com.fiap.workbalance.domain.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
