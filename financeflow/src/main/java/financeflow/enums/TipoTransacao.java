package financeflow.enums;

public enum TipoTransacao {

    RECEITA ("Receita"),
    DESPESA("Despesa");

    private String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }
    public String getTransacao() {
        return descricao;
    }
}
