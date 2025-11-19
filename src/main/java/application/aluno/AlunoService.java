package application.aluno;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepo;

    public Iterable<AlunoDTO> findAll() {
        return alunoRepo.findAll().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO findById(long id) {
        Optional<Aluno> aluno = alunoRepo.findById(id);

        if (aluno.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        return new AlunoDTO(aluno.get());
    }

    public AlunoDTO create(AlunoInsertDTO dados) {
        return new AlunoDTO(alunoRepo.save(new Aluno(dados)));
    }

    public AlunoDTO update(long id, AlunoInsertDTO dados) {
        Optional<Aluno> aluno = alunoRepo.findById(id);

        if (aluno.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        aluno.get().setNome(dados.nome());
        aluno.get().setEmail(dados.email());
        aluno.get().setDataMatricula(dados.dataMatricula());

        return new AlunoDTO(alunoRepo.save(aluno.get()));
    }

    public void delete(long id) {
        Optional<Aluno> aluno = alunoRepo.findById(id);

        if (aluno.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        alunoRepo.deleteById(id);
    }
}
