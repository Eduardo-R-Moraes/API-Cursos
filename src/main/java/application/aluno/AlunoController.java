package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Operações relacionadas a alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping
    @Operation(summary = "Listar alunos", description = "Retorna a lista de todos os alunos")
    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
    public Iterable<AlunoDTO> getAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter aluno por ID", description = "Retorna os dados de um aluno a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public AlunoDTO getById(@Parameter(description = "ID do aluno", required = true) @PathVariable long id) {
        return alunoService.findById(id);
    }

    @PostMapping()
    @Operation(summary = "Criar aluno", description = "Cria um novo aluno com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso")
    public AlunoDTO create(@RequestBody AlunoInsertDTO dados) {
        return alunoService.create(dados);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados do aluno identificado pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public AlunoDTO update(@Parameter(description = "ID do aluno", required = true) @PathVariable long id, @RequestBody AlunoInsertDTO dados) {
        return alunoService.update(id, dados);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remover aluno", description = "Remove o aluno identificado pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Aluno removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public void delete(@Parameter(description = "ID do aluno", required = true) @PathVariable long id) {
        alunoService.delete(id);
    }
}
