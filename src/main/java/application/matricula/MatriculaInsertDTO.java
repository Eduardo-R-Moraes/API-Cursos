package application.matricula;

import java.time.LocalDateTime;

public record MatriculaInsertDTO(
    LocalDateTime dataMatricula,
    String status, 
    long alunoId, 
    long cursoId) {
    
}
