package ist.meic.pa.GenericFunctionsExtended.combinator;

import ist.meic.pa.GenericFunctionsExtended.sorter.Sorter;
import ist.meic.pa.GenericFunctionsExtended.sorter.StandardSorter;

public abstract class SortedCombinator extends Combinator {
    protected Sorter sorter = null;

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public void prepare(Object[] args) {
        if (this.sorter == null) {
            this.sorter = new StandardSorter(args);
        } else {
            this.sorter.setArgs(args);
        }
    }
}
