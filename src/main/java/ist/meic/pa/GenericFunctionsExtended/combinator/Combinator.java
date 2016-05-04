package ist.meic.pa.GenericFunctionsExtended.combinator;

import ist.meic.pa.GenericFunctionsExtended.GenericFunction;

public abstract class Combinator {

    protected GenericFunction genericFunction;
    protected boolean callNextMethod = false;

    public Combinator() {
    }

    public void setGenericFunction(GenericFunction genericFunction) {
        this.genericFunction = genericFunction;
    }

    public void callNextMethod() {
        this.callNextMethod = true;
    }

    public abstract Object execute(Object ... args);
}
