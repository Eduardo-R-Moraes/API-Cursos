package application.matricula;

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
@RequestMapping("/matriculas")
@Tag(name = "Matrículas", description = "Operações relacionadas a matrículas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    @Operation(summary = "Listar matrículas", description = "Retorna a lista de todas as matrículas")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso")
    public Iterable<MatriculaDTO> getAll() {
        return matriculaService.findAll(); 
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter matrícula por ID", description = "Retorna os dados de uma matrícula a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO getById(@Parameter(description = "ID da matrícula", required = true) @PathVariable long id) {
        return matriculaService.findById(id);
    }

    @GetMapping("/aluno/{id}")
    @Operation(summary = "Listar matrículas por aluno", description = "Retorna matrículas associadas a um aluno")
    @ApiResponse(responseCode = "200", description = "Matrículas do aluno retornadas com sucesso")
    public Iterable<MatriculaDTO> getByAlunoId(@Parameter(description = "ID do aluno", required = true) @PathVariable long id) {
        return matriculaService.findByAlunoId(id);
    }

    @GetMapping("/curso/{id}")
    @Operation(summary = "Listar matrículas por curso", description = "Retorna matrículas associadas a um curso")
    @ApiResponse(responseCode = "200", description = "Matrículas do curso retornadas com sucesso")
    public Iterable<MatriculaDTO> getByCursoId(@Parameter(description = "ID do curso", required = true) @PathVariable long id) {
        return matriculaService.findByCursoId(id);
    }

    @PostMapping
    @Operation(summary = "Criar matrícula", description = "Cria uma nova matrícula com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Matrícula criada com sucesso")
    public MatriculaDTO create(@RequestBody MatriculaInsertDTO dados) {
        return matriculaService.create(dados);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar matrícula", description = "Atualiza os dados da matrícula identificada pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO update(@Parameter(description = "ID da matrícula", required = true) @PathVariable long id, @RequestBody MatriculaInsertDTO dados) {
        return matriculaService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover matrícula", description = "Remove a matrícula identificada pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Matrícula removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public void delete(@Parameter(description = "ID da matrícula", required = true) @PathVariable long id) {
        matriculaService.delete(id);
    }

}
