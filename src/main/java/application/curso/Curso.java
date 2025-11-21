package application.curso;

import java.time.LocalDateTime;

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
@Table(name = "cursos")
@Setter
@Getter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    @Column(nullable = false)
    private String status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    public Curso(CursoDTO cursodto) {
        this.id = cursodto.id();
        this.nome = cursodto.nome();
        this.descricao = cursodto.descricao();
        this.cargaHoraria = cursodto.cargaHoraria();
        this.status = cursodto.status();
        this.dataCriacao = cursodto.dataCriacao();
    }

    public Curso(CursoInsertDTO cursodto) {
        this.nome = cursodto.nome();
        this.descricao = cursodto.descricao();
        this.cargaHoraria = cursodto.cargaHoraria();
        this.status = cursodto.status();
        this.dataCriacao = cursodto.dataCriacao();
    }
}
