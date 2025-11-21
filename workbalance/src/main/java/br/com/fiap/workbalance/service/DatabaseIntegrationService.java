package br.com.fiap.workbalance.service;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.fiap.workbalance.api.dto.CheckInRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class DatabaseIntegrationService {

    @PersistenceContext
    private EntityManager entityManager;

    // 1) Chamar a PROCEDURE PR_CHECKIN_INS para inserir check-in
    public Long registrarCheckInViaProcedure(CheckInRequest request) {
        StoredProcedureQuery sp = entityManager
                .createStoredProcedureQuery("PR_CHECKIN_INS");

        sp.registerStoredProcedureParameter("p_id", Long.class, jakarta.persistence.ParameterMode.OUT);
        sp.registerStoredProcedureParameter("p_usuario_id", Long.class, jakarta.persistence.ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_humor", Integer.class, jakarta.persistence.ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_nivel_estresse", Integer.class, jakarta.persistence.ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_qualidade_sono", Integer.class, jakarta.persistence.ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_sintomas", String.class, jakarta.persistence.ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_observacoes", String.class, jakarta.persistence.ParameterMode.IN);

        sp.setParameter("p_usuario_id", request.getUsuarioId());
        sp.setParameter("p_humor", request.getHumor());
        sp.setParameter("p_nivel_estresse", request.getNivelEstresse());
        sp.setParameter("p_qualidade_sono", request.getQualidadeSono());
        sp.setParameter("p_sintomas", request.getSintomasFisicos());
        sp.setParameter("p_observacoes", request.getObservacoes());

        sp.execute();

        Object idGerado = sp.getOutputParameterValue("p_id");
        return idGerado != null ? ((Number) idGerado).longValue() : null;
    }

    // 2) Função FN_MEDIA_HUMOR_EQUIPE
    public Double calcularMediaHumorEquipe(Long equipeId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        Object result = entityManager.createNativeQuery(
                        "SELECT fn_media_humor_equipe(:equipeId, :dataIni, :dataFim) FROM dual")
                .setParameter("equipeId", equipeId)
                .setParameter("dataIni", Date.valueOf(dataInicio.toLocalDate()))
                .setParameter("dataFim", Date.valueOf(dataFim.toLocalDate()))
                .getSingleResult();

        return result != null ? ((Number) result).doubleValue() : null;
    }

    // 3) Função FN_INDICE_RISCO_EQUIPE
    public String obterIndiceRiscoEquipe(Long equipeId) {
        Object result = entityManager.createNativeQuery(
                        "SELECT fn_indice_risco_equipe(:equipeId) FROM dual")
                .setParameter("equipeId", equipeId)
                .getSingleResult();

        return result != null ? result.toString() : "SEM_DADOS";
    }

    // 4) Função FN_PODE_FAZER_CHECKIN
    public boolean podeFazerCheckinHoje(Long usuarioId) {
        Object result = entityManager.createNativeQuery(
                        "SELECT fn_pode_fazer_checkin(:usuarioId) FROM dual")
                .setParameter("usuarioId", usuarioId)
                .getSingleResult();

        return "SIM".equalsIgnoreCase(result.toString());
    }
}
