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

    private String name;

    public GenericFunction(String name) {
        this.name = name;
    }

    public boolean addMethod(GFMethod method) {
        /*if ( ! method.returns(Object.class)) {
            throw new IllegalArgumentException("GFMethod used as primary method must return Object");
        }*/
        return this.methods.add(method);
    }

    public boolean addBeforeMethod(GFMethod method) {
        if ( ! method.returns(Void.TYPE)) {
            throw new IllegalArgumentException("GFMethod used as before method must return void");
        }
        return this.beforeMethods.add(method);
    }

    public boolean addAfterMethod(GFMethod method) {
        if ( ! method.returns(Void.TYPE)) {
            throw new IllegalArgumentException("GFMethod used as after method must return void");
        }
        return this.afterMethods.add(method);
    }

    public Object call(Object ... args) {
        boolean methodExists = this.methods.stream().anyMatch(method -> method.isApplicable(args));

        if ( ! methodExists) {
            throw new IllegalArgumentException("No methods for generic function " + this.name +
                            " with args " + args + " of classes ");
            // FIXME: check how to print the classes of the args
        }

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