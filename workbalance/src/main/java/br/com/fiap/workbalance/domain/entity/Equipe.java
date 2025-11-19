package br.com.fiap.workbalance.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String setor;

    @OneToMany(mappedBy = "equipe")
    private List<Usuario> membros = new ArrayList<>();

    public Equipe() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public List<Usuario> getMembros() { return membros; }
    public void setMembros(List<Usuario> membros) { this.membros = membros; }
}
