package application.matricula;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> { 
    public List<Matricula> findByAlunoId(long alunoId);
    public List<Matricula> findByCursoId(long cursoId);
}
