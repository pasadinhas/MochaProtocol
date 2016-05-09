package ist.meic.pa.GenericFunctions.util;

class StackElement {
    public Integer depth;
    public Class<?> type;
    public Integer dimension;
    public Class<?> componentType;

    public StackElement(Integer depth, Class<?> type, Integer dimension, Class<?> componentType) {
        this.depth = depth;
        this.type = type;
        this.dimension = dimension;
        this.componentType = componentType;
    }

    public StackElement(Integer depth, Class<?> type) {
        this(depth, type, 0, type);
    }
}
