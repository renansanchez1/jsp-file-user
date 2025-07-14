package br.com.ifms.modelo;


public class Filme {
    private Long id;
    private String nome;    
    private String classificacao;
    
    public Filme() {
        super();
    }
    
    public Filme(String nome, String classificacao) {
        this.nome = nome;
        this.classificacao = classificacao;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return "Filme [id=" + id + ", nome=" + nome + ", classificacao=" + classificacao + "]";
    } 
}

