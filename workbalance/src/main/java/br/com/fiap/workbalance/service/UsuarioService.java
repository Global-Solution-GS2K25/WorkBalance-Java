package br.com.fiap.workbalance.service;

import br.com.fiap.workbalance.api.dto.UsuarioRequest;
import br.com.fiap.workbalance.api.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse criar(UsuarioRequest request);
    List<UsuarioResponse> listarTodos();
}
