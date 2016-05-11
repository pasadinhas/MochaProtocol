package ist.meic.pa.GenericFunctionsExtended.util;

import java.lang.reflect.Array;
import java.util.Stack;

public class TypeChecker {

    public static boolean isCompatible(Object object, Class<?> type) {
        return type.isInstance(object);
    }

    public static int computeDistance(Object object, Class<?> type) {
        return object.getClass().isArray() ?
                computeDistanceArray(object, type) :
                computeDistanceNotArray(object, type);
    }

    private static int computeDistanceNotArray(Object object, Class<?> type) {
        Stack<StackElement> stack = new Stack<>();

        stack.add(new StackElement(0, object.getClass()));

        while ( ! stack.isEmpty()) {
            StackElement element = stack.pop();

            if (type.equals(element.type)) {
                return element.depth;
            }

            if (element.type.getSuperclass() != null) {
                stack.add(new StackElement(element.depth + 1, element.type.getSuperclass()));
            }

            for (Class<?> interfaceType : element.type.getInterfaces()) {
                stack.add(new StackElement(element.depth + 1, interfaceType));
            }
        }

        throw new RuntimeException("Cannot compute distance from types: " + object.getClass() + " --> " + type);
    }

    private static int computeDistanceArray(Object object, Class<?> type) {
        Class<?> objectType = object.getClass();
        Class<?> componentType = computeComponentType(objectType);
        int arrayDimension = computeArrayDimension(objectType);

        Stack<StackElement> stack = new Stack<>();
        stack.add(new StackElement(0, objectType, arrayDimension, componentType));

        while (!stack.isEmpty()) {
            StackElement element = stack.pop();

            if (type.equals(element.type)) {
                return element.depth;
            }

            if (Object.class.equals(element.componentType)) {
                if (element.dimension == 1) {
                    return element.depth + 1;
                }

                stack.add(new StackElement(
                        element.depth + 1,
                        classForArray(Object.class, element.dimension - 1),
                        element.dimension - 1,
                        element.componentType));
                continue;
            }

            for (Class<?> interfaceType : element.componentType.getInterfaces()) {
                stack.add(new StackElement(
                        element.depth + 1,
                        classForArray(interfaceType, element.dimension),
                        element.dimension,
                        interfaceType));
            }

            Class<?> superclass = element.componentType.getSuperclass();

            if (superclass != null) {
                stack.add(new StackElement(
                        element.depth + 1,
                        classForArray(superclass, element.dimension),
                        element.dimension,
                        superclass));
            }
        }
        throw new RuntimeException("Cannot compute distance from types: " + object.getClass() + " --> " + type);
    }

    private static int computeArrayDimension(Class<?> type) {
        int counter = 0;
        while (type.isArray()) {
            counter++;
            type = type.getComponentType();
        }
        return counter;
    }

    private static Class<?> computeComponentType(Class<?> type) {
        while (type.isArray()) {
            type = type.getComponentType();
        }
        return type;
    }

    private static Class<?> classForArray(Class<?> componentType, int dimension) {
        return Array.newInstance(componentType, dimension).getClass();
    }
}
