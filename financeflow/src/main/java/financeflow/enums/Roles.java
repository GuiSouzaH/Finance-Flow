package financeflow.enums;

public enum Roles {
    USER ("Usuário"),
    ADMIN ("Administrador");

    private String descricao;

    Roles (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    }
