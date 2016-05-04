package ist.meic.pa.GenericFunctionsExtended.util;

class StackElement {
    public Integer depth;
    public Class<?> type;

    public StackElement(Integer depth, Class<?> type) {
        this.depth = depth;
        this.type = type;
    }
}
