package ist.meic.pa.GenericFunctionsExtended.sorter;

import ist.meic.pa.GenericFunctionsExtended.GFMethod;
import ist.meic.pa.GenericFunctionsExtended.util.TypeChecker;

public class StandardSorter extends Sorter {

    public StandardSorter(Object[] args) {
        super(args);
    }

    @Override
    public int compare(GFMethod m1, GFMethod m2) {
        Class<?>[] params1 = m1.getMethod().getParameterTypes();
        Class<?>[] params2 = m2.getMethod().getParameterTypes();

        int compare = 0;

        for (int i = 0; i < args.length; i++) {
            compare = Integer.compare(
                    TypeChecker.computeDistance(args[i], params1[i]),
                    TypeChecker.computeDistance(args[i], params2[i]));

            if (compare != 0) {
                return compare;
            }
        }

        return compare;
    }
}
