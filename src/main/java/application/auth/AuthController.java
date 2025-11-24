package application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas a autenticação e autorização")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Fazer login", description = "Autentica um usuário e retorna um token JWT válido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso, retorna token JWT"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public String login(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken tk =
            new UsernamePasswordAuthenticationToken(
                usuario.getNomeDeUsuario(), usuario.getSenha());
        authenticationManager.authenticate(tk);
        
        return tokenService.generateToken(usuario);
    }
}