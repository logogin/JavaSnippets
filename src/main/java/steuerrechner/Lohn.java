package steuerrechner;

import java.math.BigDecimal;

/**
 * Lohn.java
 *
 * @author Pavel Danchenko
 * @date Nov 1, 2014
 *
 */
public class Lohn {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Lohnsteuer2013Big calc = new Lohnsteuer2013Big();
        calc.STKL = 1;
        calc.KRV = 1;
        calc.LZZ = 1;
        calc.RE4 = BigDecimal.valueOf(10000);

        //calc.LZZ = 2;
        //calc.RE4 = BigDecimal.valueOf(458300);

        calc.VBEZ = BigDecimal.ZERO;
        calc.LZZFREIB = BigDecimal.ZERO;
        calc.LZZHINZU = BigDecimal.ZERO;
        calc.ZKF = BigDecimal.ZERO;
        calc.SONSTB = BigDecimal.ZERO;
        calc.VKAPA = BigDecimal.ZERO;
        calc.VMT = BigDecimal.ZERO;

        calc.main();

        System.out.println("LSTLZZ: " + calc.LSTLZZ);
        System.out.println("SOLZLZZ: " + calc.SOLZLZZ);
        System.out.println("Total: " + calc.LSTLZZ.add(calc.SOLZLZZ));
        System.out.println("SOLZS: " + calc.LSTLZZ.add(calc.SOLZS));
    }

}
