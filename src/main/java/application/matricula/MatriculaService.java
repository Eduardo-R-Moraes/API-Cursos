package application.matricula;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.aluno.Aluno;
import application.aluno.AlunoService;
import application.curso.Curso;
import application.curso.CursoService;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepo;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    public Iterable<MatriculaDTO> findAll() {
        return matriculaRepo.findAll().stream().map(MatriculaDTO::new).toList();
    }


    public Iterable<MatriculaDTO> findByAlunoId(long alunoId) {
        return matriculaRepo.findByAlunoId(alunoId).stream().map(MatriculaDTO::new).toList();
    }


    public Iterable<MatriculaDTO> findByCursoId(long cursoId) {
        return matriculaRepo.findByAlunoId(cursoId).stream().map(MatriculaDTO::new).toList();
    }


    public MatriculaDTO findById(long id) {
        Optional<Matricula> matricula = matriculaRepo.findById(id);

        if (matricula.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula não encontrado"
            );
        }

        return new MatriculaDTO(matricula.get());
    }


    public MatriculaDTO create(MatriculaInsertDTO dados) {
        Aluno aluno = new Aluno(alunoService.findById(dados.alunoId()));
        Curso curso = new Curso(cursoService.findById(dados.cursoId()));

        Matricula matricula = new Matricula();
        matricula.setDataMatricula(dados.dataMatricula());
        matricula.setStatus(dados.status());
        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        return new MatriculaDTO(matriculaRepo.save(matricula));
    }


    public MatriculaDTO update(long id, MatriculaInsertDTO dados) {
        Optional<Matricula> matricula = matriculaRepo.findById(id);

        if (matricula.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula não encontrado"
            );
        }

        Aluno aluno = new Aluno(alunoService.findById(dados.alunoId()));
        Curso curso = new Curso(cursoService.findById(dados.cursoId()));

        matricula.get().setDataMatricula(dados.dataMatricula());
        matricula.get().setStatus(dados.status());
        matricula.get().setAluno(aluno);
        matricula.get().setCurso(curso);

        return new MatriculaDTO(matriculaRepo.save(matricula.get()));
    }

    public void delete(long id) {
        Optional<Matricula> matricula = matriculaRepo.findById(id);

        if (matricula.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula não encontrado"
            );
        }

        matriculaRepo.deleteById(id);
    }
}
