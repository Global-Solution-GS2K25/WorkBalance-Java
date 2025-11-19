package br.com.fiap.workbalance.api.dto;

import br.com.fiap.workbalance.domain.enums.RoleUsuario;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private RoleUsuario role;

    public UsuarioResponse() {}

    public UsuarioResponse(Long id, String nome, String email, RoleUsuario role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public RoleUsuario getRole() { return role; }
    public void setRole(RoleUsuario role) { this.role = role; }
}
