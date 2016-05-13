package ist.meic.pa.GenericFunctions;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miguel on 20/04/16.
 */
public class Test {

    public static void main(String[] args) {
        final GenericFunction add = new GenericFunction("add");

        add.addMethod(new GFMethod() {
            public Object call(Integer a, Integer b) {
                return a + b;
            }});

        add.addMethod(new GFMethod() {
            Object call(Object[] a, Object[] b) {
                Object[] r = new Object[a.length];
                for (int i = 0; i < a.length; i++) {
                    r[i] = add.call(a[i], b[i]);
                }
                return r;
            }});

        add.addMethod(new GFMethod() {
            Object call(Object[] a, Object b) {
                Object[] ba = new Object[a.length];
                Arrays.fill(ba, b);
                return add.call(a, ba);
            }});

        add.addMethod(new GFMethod() {
            Object call(Object a, Object b[]) {
                Object[] aa = new Object[b.length];
                Arrays.fill(aa, a);
                return add.call(aa, b);
            }});

        add.addMethod(new GFMethod() {
            Object call(String a, Object b) {
                return add.call(Integer.decode(a), b);
            }});

        add.addMethod(new GFMethod() {
            Object call(Object a, String b) {
                return add.call(a, Integer.decode(b));
            }});

        add.addMethod(new GFMethod() {
            Object call(Object[] a, List b) {
                return add.call(a, b.toArray());
            }});

        println(add.call(2, 3));
        println(add.call(new Object[]{1, 2}, 3));
        println(add.call(1, new Object[][]{{1, 2}, {3, 4}}));
        println(add.call("12", "34"));
        println(add.call(new Object[] { "123", "4" }, 5));
        println(add.call(new Object[] { 1, 2, 3 }, Arrays.asList(4, 5, 6)));
    }

    public static void println(Object obj) {
        if (obj instanceof Object[]) {
            System.out.println(Arrays.deepToString((Object[]) obj));
        } else {
            System.out.println(obj);
        }
    }
}
