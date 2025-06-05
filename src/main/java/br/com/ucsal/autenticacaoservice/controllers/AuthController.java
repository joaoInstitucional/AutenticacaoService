package br.com.ucsal.autenticacaoservice.controllers;

import br.com.ucsal.autenticacaoservice.dtos.ApiResponse;
import br.com.ucsal.autenticacaoservice.dtos.LoginDTO;
import br.com.ucsal.autenticacaoservice.dtos.RegisterDTO;
import br.com.ucsal.autenticacaoservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO dto) {
        boolean auth = service.authenticate(dto.email(), dto.password());
        if (auth) {
            // Exemplo de retorno de JWT no futuro
            String token = "jwt_token_exemplo";

            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Autenticado com sucesso!", token)
            );
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(401, "Credenciais inválidas", null));
    }

   @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDTO dto) {
        service.register(dto.email(), dto.password(), dto.userId());

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Usuário registrado com sucesso.", null)
        );
    }
}