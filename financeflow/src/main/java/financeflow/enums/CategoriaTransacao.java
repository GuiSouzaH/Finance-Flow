package financeflow.enums;

public enum CategoriaTransacao {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    MORADIA("Moradia"),
    SAUDE("Saúde"),
    LAZER("Lazer"),
    EDUCACAO ("Educação"),
    OUTROS ("Outros");

    private String descricao;

    CategoriaTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return descricao;
    }

}
