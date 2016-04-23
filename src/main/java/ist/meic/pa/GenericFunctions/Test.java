package ist.meic.pa.GenericFunctions;

import java.util.Arrays;

/**
 * Created by miguel on 20/04/16.
 */
public class Test {

    public static void main(String[] args) {
        final GenericFunction explain = new GenericFunction("explain");
        explain.addMethod(new GFMethod() {
            Object call(Integer entity) {
                System.out.printf("%s is a integer", entity);
                return "";
            }});
        explain.addMethod(new GFMethod() {
            Object call(Number entity) {
                System.out.printf("%s is a number", entity);
                return "";
            }});
        explain.addMethod(new GFMethod() {
            Object call(String entity) {
                System.out.printf("%s is a string", entity);
                return "";
            }});
        explain.addAfterMethod(new GFMethod() {
            void call(Integer entity) {
                System.out.printf(" (in hexadecimal, is %x)", entity);
            }});
        explain.addBeforeMethod(new GFMethod() {
            void call(Number entity) {
                System.out.printf("The number ", entity);
            }});
        println(explain.call(123));
        println(explain.call("Hi"));
        println(explain.call(3.14159));    }

    public static void println(Object obj) {
        if (obj instanceof Object[]) {
            System.out.println(Arrays.deepToString((Object[]) obj));
        } else {
            System.out.println(obj);
        }
    }
}
