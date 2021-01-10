package calcutils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcUtilsTest {

    @Test
    public void normalCalcul() {
        CalcUtils calc = new CalcUtils();
        calc.add(15);
        calc.add(Operator.PLUS);
        calc.add(-10);
        calc.add(Operator.MINUS);
        calc.add(-10);
        calc.add(Operator.DIVIDE);
        calc.add(5);
        calc.add(Operator.MODULO);
        calc.add(2);

        assertEquals("1.0", calc.calculate());
    }

    @Test
    public void specialCalcul() {

        assertEquals("0.28366218546322625",CalcUtils.specialCalcul(5,Special.COS));
        assertEquals("0.1411200080598672",CalcUtils.specialCalcul(3,Special.SIN));
        assertEquals("1.5574077246549023",CalcUtils.specialCalcul(1,Special.TAN));
        assertEquals("5.477225575051661",CalcUtils.specialCalcul(30,Special.SQRT));
        assertEquals("24.0",CalcUtils.specialCalcul(4,Special.FACT));



    }
}