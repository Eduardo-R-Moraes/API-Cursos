package application.aluno;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Setter
@Getter
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula; 

    public Aluno(AlunoDTO alunodto) {
        this.id = alunodto.id();
        this.nome = alunodto.nome();
        this.email = alunodto.email();
        this.dataMatricula = alunodto.dataMatricula();
    }

    public Aluno(AlunoInsertDTO alunodto) {
        this.nome = alunodto.nome();
        this.email = alunodto.email();
        this.dataMatricula = alunodto.dataMatricula();
    }
}
