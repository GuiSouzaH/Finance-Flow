package financeflow.enums;

public enum TipoArquivoAceito {
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    APPLICATION_PDF("application/pdf");

    private final String mimeType;

    TipoArquivoAceito(String mimeType) {
        this.mimeType = mimeType;
    }

    public static boolean isAceito(String mimeType) {
        for (TipoArquivoAceito tipo : TipoArquivoAceito.values()) {
            if (tipo.mimeType.equals(mimeType)) {
                return true;
            }
        }
        return false;
    }
}

