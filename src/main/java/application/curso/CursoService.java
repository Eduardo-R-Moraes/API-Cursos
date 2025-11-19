package application.curso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Iterable<CursoDTO> findAll() {
        return cursoRepository.findAll().stream().map(CursoDTO::new).toList();
    }

    public CursoDTO findById(long id) {
        Optional<Curso> curso = cursoRepository.findById(id);

        if (curso.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        return new CursoDTO(curso.get());
    }

    public CursoDTO create(CursoInsertDTO dados) {
        return new CursoDTO(cursoRepository.save(new Curso(dados)));
    }

    public CursoDTO update(long id, CursoInsertDTO dados) {
        Optional<Curso> curso = cursoRepository.findById(id);

        if (curso.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        curso.get().setNome(dados.nome());
        curso.get().setDescricao(dados.descricao());
        curso.get().setCargaHoraria(dados.cargaHoraria());
        curso.get().setStatus(dados.status());

        return new CursoDTO(cursoRepository.save(curso.get()));
    }

    public void delete(long id) {
        Optional<Curso> curso = cursoRepository.findById(id);

        if (curso.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        cursoRepository.deleteById(id);
    }
}
