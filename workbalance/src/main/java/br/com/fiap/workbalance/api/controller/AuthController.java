package br.com.fiap.workbalance.api.controller;

import br.com.fiap.workbalance.api.dto.LoginRequest;
import br.com.fiap.workbalance.api.dto.TokenResponse;
import br.com.fiap.workbalance.api.dto.UsuarioRequest;
import br.com.fiap.workbalance.api.dto.UsuarioResponse;
import br.com.fiap.workbalance.security.JwtService;
import br.com.fiap.workbalance.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
