package ist.meic.pa.GenericFunctions.sorter;

import ist.meic.pa.GenericFunctions.GFMethod;

import java.util.Comparator;

abstract public class Sorter implements Comparator<GFMethod> {

    protected Object[] args = null;

    public Sorter(Object[] args) {
        this.args = args;
    }

    abstract public int compare(GFMethod m1, GFMethod m2);
}
