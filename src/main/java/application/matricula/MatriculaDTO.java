package application.matricula;

import java.time.LocalDateTime;

import application.aluno.Aluno;
import application.curso.Curso;

public record MatriculaDTO(
    long id, 
    LocalDateTime dataMatricula, 
    String status, 
    Aluno aluno, 
    Curso curso) {
    public MatriculaDTO(Matricula matricula) {
        this(
            matricula.getId(), 
            matricula.getDataMatricula(), 
            matricula.getStatus(), 
            matricula.getAluno(), 
            matricula.getCurso()
        );
    }
}
