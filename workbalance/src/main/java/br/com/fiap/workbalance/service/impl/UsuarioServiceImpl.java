package br.com.fiap.workbalance.service.impl;

import br.com.fiap.workbalance.api.dto.UsuarioRequest;
import br.com.fiap.workbalance.api.dto.UsuarioResponse;
import br.com.fiap.workbalance.domain.entity.Usuario;
import br.com.fiap.workbalance.domain.repository.UsuarioRepository;
import br.com.fiap.workbalance.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setRole(request.getRole());
        usuario.setSenhaHash(passwordEncoder.encode(request.getSenha()));
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponse(salvo.getId(), salvo.getNome(), salvo.getEmail(), salvo.getRole());
    }

    @Override
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
    }
}
