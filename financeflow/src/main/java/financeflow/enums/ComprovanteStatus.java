package financeflow.enums;

public enum ComprovanteStatus {
    PENDENTE ("Pendente"),
    PROCESSANDO ("Processando"),
    CONCLUIDO ("Concluído"),
    ERRO ("Erro");

    private final String descricao;
    private ComprovanteStatus(String descricao) {
        this.descricao = descricao;
    }
}
