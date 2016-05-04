package ist.meic.pa.GenericFunctionsExtended;

import ist.meic.pa.GenericFunctionsExtended.combinator.AroundCombinator;

import java.util.Arrays;

/**
 * Created by miguel on 20/04/16.
 */
public class Test {

    public class Shape {}
    public class Rectangle extends Shape {}
    public class Circle extends Shape {}
    public class Square extends Rectangle {}

    public static void main(String[] args) {
        new Test().test();
    }

    public void test() {
        final GenericFunction draw = new GenericFunction("draw");

        draw.addBeforeMethod(new GFMethod() {
            public Object call(Rectangle x) {
                System.out.println("Before - Rectangle");
                return new Rectangle();
            }
        });

        draw.addPrimaryMethod(new GFMethod() {
            public Object call(Square x) {
                draw.callNextMethod();
                System.out.println("Primary - Square");
                return new Square();
            }
        });

        draw.addPrimaryMethod(new GFMethod() {
            public Object call(Rectangle x) {
                System.out.println("Primary - Rectangle");
                return new Square();
            }
        });

        draw.addAroundMethod(new GFMethod() {
            public Object call(Shape x) {
                draw.callNextMethod();
                System.out.println("Around - Shape");
                return new Shape();
            }
        });

        draw.addAroundMethod(new GFMethod() {
            public Object call(Rectangle x) {
                draw.callNextMethod();
                System.out.println("Around - Rectangle");
                return new Shape();
            }
        });

        draw.setCombinator(new AroundCombinator());

        println(draw.call(new Square()));
    }

    public static void println(Object obj) {
        if (obj instanceof Object[]) {
            System.out.println(Arrays.deepToString((Object[]) obj));
        } else {
            System.out.println(obj);
        }
    }
}
