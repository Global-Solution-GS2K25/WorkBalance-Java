package br.com.fiap.workbalance.api.dto;

public class TokenResponse {

    private String token;
    private String tipo = "Bearer";

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
