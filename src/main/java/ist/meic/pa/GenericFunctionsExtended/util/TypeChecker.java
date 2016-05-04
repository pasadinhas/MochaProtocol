package ist.meic.pa.GenericFunctionsExtended.util;

import java.util.Stack;

public class TypeChecker {

    public static boolean isCompatible(Object object, Class<?> type) {
        return type.isInstance(object);
    }
    public static int computeDistance(Object object, Class<?> type) {
        Stack<StackElement> stack = new Stack<>();
        stack.add(new StackElement(0, object.getClass()));
        while ( ! stack.isEmpty()) {
            StackElement element = stack.pop();
            if (type.equals(element.type)) {
                return element.depth;
            }
            if (element.type.equals(Object.class)) {
                throw new RuntimeException();
            }

            if (element.type.getSuperclass() != null) {
                stack.add(new StackElement(element.depth + 1, element.type.getSuperclass()));
            }
            for (Class<?> interfaceType : element.type.getInterfaces()) {
                stack.add(new StackElement(element.depth + 1, interfaceType));
            }
        }
        throw new RuntimeException();
    }
}
