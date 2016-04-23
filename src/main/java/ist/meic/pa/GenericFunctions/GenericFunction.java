package ist.meic.pa.GenericFunctions;

import ist.meic.pa.GenericFunctions.sorter.StandardSorter;
import ist.meic.pa.GenericFunctions.util.TypeChecker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GenericFunction {

    private List<GFMethod> methods = new ArrayList<>();
    private List<GFMethod> beforeMethods = new ArrayList<>();
    private List<GFMethod> afterMethods = new ArrayList<>();

    public GenericFunction() {}
    public GenericFunction(String name) {}

    public boolean addMethod(GFMethod method) {
        // TODO: validate return type of method
        return this.methods.add(method);
    }

    public boolean addBeforeMethod(GFMethod method) {
        // TODO: validate return type of method
        return this.beforeMethods.add(method);
    }

    public boolean addAfterMethod(GFMethod method) {
        // TODO: validate return type of method
        return this.afterMethods.add(method);
    }

    public Object call(Object ... args) {
        this.beforeMethods.stream()
                .filter(method -> method.isApplicable(args))
                .sorted(new StandardSorter(args))
                .forEach(method -> method.invoke(args));

        Object result = this.methods.stream()
                .filter(method -> method.isApplicable(args))
                .sorted(new StandardSorter(args))
                .findFirst()
                .get()
                .invoke(args);

        this.afterMethods.stream()
                .filter(method -> method.isApplicable(args))
                .sorted(new StandardSorter(args))
                .forEach(method -> method.invoke(args));

        return result;
    }

}