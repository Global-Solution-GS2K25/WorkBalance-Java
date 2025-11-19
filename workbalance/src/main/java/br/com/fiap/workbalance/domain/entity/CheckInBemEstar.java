package br.com.fiap.workbalance.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_checkin")
public class CheckInBemEstar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int humor;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int nivelEstresse;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int qualidadeSono;

    @Column(length = 500)
    private String sintomasFisicos;

    @Column(length = 1000)
    private String observacoes;

    public CheckInBemEstar() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

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
