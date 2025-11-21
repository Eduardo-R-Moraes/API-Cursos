package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Operações relacionadas a cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    // Get
    @GetMapping
    @Operation(summary = "Listar cursos", description = "Retorna a lista de todos os cursos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    public Iterable<CursoDTO> getAll() {
        return cursoService.findAll();
    }


    // Get by ID
    @GetMapping("/{id}")
    @Operation(summary = "Obter curso por ID", description = "Retorna os dados de um curso a partir do seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO getById(@Parameter(description = "ID do curso", required = true) @PathVariable long id) {
        return cursoService.findById(id);
    }


    // Post
    @PostMapping
    @Operation(summary = "Criar curso", description = "Cria um novo curso com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Curso criado com sucesso")
    public CursoDTO create(@RequestBody CursoInsertDTO dados) {
        return cursoService.create(dados);
    }


    // Put
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza os dados do curso identificado pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO update(
        @Parameter(description = "ID do curso", required = true) 
        @PathVariable long id, 
        @RequestBody CursoInsertDTO dados) {
        return cursoService.update(id, dados); 
    }


    // Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover curso", description = "Remove o curso identificado pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Curso removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void delete(@Parameter(description = "ID do curso", required = true) @PathVariable long id) {
        cursoService.delete(id);
    }
}
