package ist.meic.pa.GenericFunctionsExtended.combinator;

import ist.meic.pa.GenericFunctionsExtended.sorter.StandardSorter;

public class AroundCombinator extends CallNextMethodCombinator {
    @Override
    public Object execute(Object... args) {

        if (!genericFunction.hasApplicablePrimaryMethod(args)) {
            throw new IllegalArgumentException();
        }

        if (genericFunction.hasApplicableAroundMethod(args)) {
            Object aroundReturn = this.executeWhileCallNextMethod(args, genericFunction.getAroundMethods());

            if ( ! callNextMethod) {
                return aroundReturn;
            }
        }

        genericFunction.getBeforeMethods().stream()
                .filter(method -> method.isApplicable(args))
                .sorted(new StandardSorter(args))
                .forEach(method -> method.invoke(args));

        Object result = this.executeWhileCallNextMethod(args, genericFunction.getPrimaryMethods());

        genericFunction.getAfterMethods().stream()
                .filter(method -> method.isApplicable(args))
                .sorted(new StandardSorter(args))
                .forEach(method -> method.invoke(args));

        return result;
    }
}
