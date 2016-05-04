package ist.meic.pa.GenericFunctionsExtended;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ist.meic.pa.GenericFunctionsExtended.combinator.Combinator;
import ist.meic.pa.GenericFunctionsExtended.combinator.StandardCombinator;
import ist.meic.pa.GenericFunctionsExtended.util.MethodType;

public class GenericFunction {

    private HashMap<String, List<GFMethod>> methods = new HashMap<>();

    private String name;

    private Combinator combinator = null;

    public GenericFunction(String name) {
        this.name = name;
    }

    public boolean addMethod(GFMethod method, String type) {
        return this.getMethods(type).add(method);
    }

    public boolean addMethod(GFMethod method) {
        return this.addPrimaryMethod(method);
    }
    public boolean addPrimaryMethod(GFMethod method) {
        return this.addMethod(method, MethodType.PRIMARY);
    }
    public boolean addBeforeMethod(GFMethod method) {
        return this.addMethod(method, MethodType.BEFORE);
    }
    public boolean addAfterMethod(GFMethod method) {
        return this.addMethod(method, MethodType.AFTER);
    }
    public boolean addAroundMethod(GFMethod method) {
        return this.addMethod(method, MethodType.AROUND);
    }

    public List<GFMethod> getPrimaryMethods() {
        return this.getMethods(MethodType.PRIMARY);
    }
    public List<GFMethod> getBeforeMethods() {
        return this.getMethods(MethodType.BEFORE);
    }
    public List<GFMethod> getAfterMethods() {
        return this.getMethods(MethodType.AFTER);
    }
    public List<GFMethod> getAroundMethods() {
        return this.getMethods(MethodType.AROUND);
    }

    public boolean hasApplicableMethod(Object[] args, String type) {
        return this.getMethods(type).stream().anyMatch(method -> method.isApplicable(args));
    }
    public boolean hasApplicablePrimaryMethod(Object[] args) {
        return this.hasApplicableMethod(args, MethodType.PRIMARY);
    }
    public boolean hasApplicableBeforeMethod(Object[] args) {
        return this.hasApplicableMethod(args, MethodType.BEFORE);
    }
    public boolean hasApplicableAfterMethod(Object[] args) {
        return this.hasApplicableMethod(args, MethodType.AFTER);
    }
    public boolean hasApplicableAroundMethod(Object[] args) {
        return this.hasApplicableMethod(args, MethodType.AROUND);
    }

    public List<GFMethod> getMethods(String type) {
        List<GFMethod> methodList = this.methods.get(type);
        if (methodList == null) {
            methodList = new ArrayList<>();
            this.methods.put(type, methodList);
        }
        return methodList;
    }

    public Object call(Object ... args) {
        if (this.combinator == null) {
            this.combinator = new StandardCombinator();
        }

        this.combinator.setGenericFunction(this);
        return this.combinator.execute(args);
    }

    public void setCombinator(Combinator combinator) {
        this.combinator = combinator;
    }

    protected void callNextMethod() {
        this.combinator.callNextMethod();
    }

}