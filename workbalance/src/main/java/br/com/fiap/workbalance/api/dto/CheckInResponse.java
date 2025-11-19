package br.com.fiap.workbalance.api.dto;

import java.time.LocalDateTime;

public class CheckInResponse {

    private Long id;
    private Long usuarioId;
    private LocalDateTime dataHora;
    private int humor;
    private int nivelEstresse;
    private int qualidadeSono;
    private String sintomasFisicos;
    private String observacoes;

    public CheckInResponse() {}

    public CheckInResponse(Long id, Long usuarioId, LocalDateTime dataHora, int humor, int nivelEstresse,
                           int qualidadeSono, String sintomasFisicos, String observacoes) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.dataHora = dataHora;
        this.humor = humor;
        this.nivelEstresse = nivelEstresse;
        this.qualidadeSono = qualidadeSono;
        this.sintomasFisicos = sintomasFisicos;
        this.observacoes = observacoes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public int getHumor() { return humor; }
    public void setHumor(int humor) { this.humor = humor; }

    public int getNivelEstresse() { return nivelEstresse; }
    public void setNivelEstresse(int nivelEstresse) { this.nivelEstresse = nivelEstresse; }

    public int getQualidadeSono() { return qualidadeSono; }
    public void setQualidadeSono(int qualidadeSono) { this.qualidadeSono = qualidadeSono; }

    public String getSintomasFisicos() { return sintomasFisicos; }
    public void setSintomasFisicos(String sintomasFisicos) { this.sintomasFisicos = sintomasFisicos; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
