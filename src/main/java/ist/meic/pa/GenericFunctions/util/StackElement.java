package ist.meic.pa.GenericFunctions.util;

class StackElement {
    public Integer depth;
    public Class<?> type;

    public StackElement(Integer depth, Class<?> type) {
        this.depth = depth;
        this.type = type;
    }
}
