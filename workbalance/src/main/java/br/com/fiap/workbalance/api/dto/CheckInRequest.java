package br.com.fiap.workbalance.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CheckInRequest {

    @NotNull
    private Long usuarioId;

    @NotNull @Min(1) @Max(5)
    private Integer humor;

    @NotNull @Min(1) @Max(5)
    private Integer nivelEstresse;

    @NotNull @Min(1) @Max(5)
    private Integer qualidadeSono;

    @Size(max = 500)
    private String sintomasFisicos;

    @Size(max = 1000)
    private String observacoes;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Integer getHumor() { return humor; }
    public void setHumor(Integer humor) { this.humor = humor; }

    public Integer getNivelEstresse() { return nivelEstresse; }
    public void setNivelEstresse(Integer nivelEstresse) { this.nivelEstresse = nivelEstresse; }

    public Integer getQualidadeSono() { return qualidadeSono; }
    public void setQualidadeSono(Integer qualidadeSono) { this.qualidadeSono = qualidadeSono; }

    public String getSintomasFisicos() { return sintomasFisicos; }
    public void setSintomasFisicos(String sintomasFisicos) { this.sintomasFisicos = sintomasFisicos; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
