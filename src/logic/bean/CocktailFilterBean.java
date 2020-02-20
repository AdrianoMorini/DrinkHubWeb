package logic.bean;

public class CocktailFilterBean {
    private CocktailFilterType type;
    private String value;

    public CocktailFilterBean(CocktailFilterType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public CocktailFilterType getFilterType() {
        return this.type;
    }
}