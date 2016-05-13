package ist.meic.pa.GenericFunctionsExtended.combinator;

import ist.meic.pa.GenericFunctionsExtended.GFMethod;
import ist.meic.pa.GenericFunctionsExtended.sorter.StandardSorter;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by miguel on 04/05/16.
 */
public abstract class CallNextMethodCombinator extends SortedCombinator {

    protected Object executeWhileCallNextMethod(Object[] args, List<GFMethod> methods) {
        List<GFMethod> applicableMethods = methods.stream()
                .filter(method -> method.isApplicable(args))
                .sorted(this.sorter)
                .collect(toList());

        Object returnValue = applicableMethods.get(0).invoke(args);
        applicableMethods.remove(0);

        while (callNextMethod && !applicableMethods.isEmpty()) {
            callNextMethod = false;
            applicableMethods.get(0).invoke(args);
            applicableMethods.remove(0);
        }

        return returnValue;
    }
}
