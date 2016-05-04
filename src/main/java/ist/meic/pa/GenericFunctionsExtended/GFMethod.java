package ist.meic.pa.GenericFunctionsExtended;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ist.meic.pa.GenericFunctionsExtended.util.TypeChecker;

abstract public class GFMethod {

    private Method method;

    public GFMethod() {
        method = validate();
    }

    private Method validate() {
        boolean found = false;
        Method call = null;
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.getName().equals("call")) {
                if (found) {
                    throw new RuntimeException("Cannot declare call twice");
                }

                call = method;
                found = true;
            }
        }
        if ( ! found) {
            throw new RuntimeException("Must declare call");
        }

        return call;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isApplicable(Object... args) {
        if (args.length != this.method.getParameterCount()) {
            return false;
        }

        Class<?>[] parameterTypes = this.method.getParameterTypes();

        for (int i = 0; i < args.length; i++) {
            if ( ! TypeChecker.isCompatible(args[i], parameterTypes[i])) {
                return false;
            }
        }

        return true;
    }

    public Object invoke(Object ... args) {
        try {
            return this.method.invoke(this, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean returns(Class<?> type) {
        return this.method.getReturnType().equals(type);
    }
}