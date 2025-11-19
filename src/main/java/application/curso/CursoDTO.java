package application.curso;

public record CursoDTO(
    long id,
    String nome,
    String descricao,
    int cargaHoraria,
    String status,
    String dataCriacao
) {
    public CursoDTO(Curso curso) {
        this(
            curso.getId(),
            curso.getNome(),
            curso.getDescricao(),
            curso.getCargaHoraria(),
            curso.getStatus(),
            curso.getDataCriacao().toString()
        );
    }
}
