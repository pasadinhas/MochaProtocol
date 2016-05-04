package ist.meic.pa.GenericFunctionsExtended.sorter;

import java.util.Comparator;

import ist.meic.pa.GenericFunctionsExtended.GFMethod;

abstract public class Sorter implements Comparator<GFMethod> {

    protected Object[] args = null;

    public Sorter(Object[] args) {
        this.args = args;
    }

    abstract public int compare(GFMethod m1, GFMethod m2);
}
