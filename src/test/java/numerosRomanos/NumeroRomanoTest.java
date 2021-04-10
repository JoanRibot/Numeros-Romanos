package numerosRomanos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class NumeroRomanoTest {

    public static NumeroRomano numeroRomano;

    @BeforeClass
    public static void setup() {
        numeroRomano = new NumeroRomano();
        numeroRomano.initRegexDicionario();
    }

    /**
     * Grupos sumatorios M, C, X, I
     */

    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)1000, numeroRomano.toDecimal());

        testCase = "MM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)2000, numeroRomano.toDecimal());

        testCase = "MMM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)3000, numeroRomano.toDecimal());

        testCase = "CM";
        numeroRomano.setNumeroRomano(testCase);
        assertNotEquals((short)1000, numeroRomano.toDecimal());

        /**
         * El caso MMMM es control de errores 
         * y no puede estas en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que programar la gestion de errores
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "MMMCCC";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)3300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "MMMXXX";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals((short)3030, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "MMMIII";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals((short)3003, numeroRomano.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "MMMDIII";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)3503, numeroRomano.toDecimal());

        testCase = "MMMCDIII";
        numeroRomano.setNumeroRomano(testCase);
        assertNotEquals((short)3503, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
     * IV(4), IX(9), 
     * XL(40), XC(90), 
     * CD(400), CM(900)
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "CD";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)400, numeroRomano.toDecimal());

        testCase = "CM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "XL";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)40, numeroRomano.toDecimal());  

        testCase = "XC";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)90, numeroRomano.toDecimal());        
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "IV";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)4, numeroRomano.toDecimal());  

        testCase = "IX";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals((short)9, numeroRomano.toDecimal());  
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String test = "MMMDCCCLXXXVIII"; // 3888
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)3888, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String test = "MMDCCLXXVII"; // 2777
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)2777, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String test = "CDXLIV"; // 444
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)444, numeroRomano.toDecimal());

        test = "CDXXXIX"; // 439
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)439, numeroRomano.toDecimal());
    }

    @Test
    public void initArrayRegex_test() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().size());
        assertEquals((short)5, numeroRomano.valorDecimal(test));
        assertEquals("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", numeroRomano.getRegexDiccionario().get("grupoSumatorio"));
		assertEquals("(C[DM])|(X[LC])|(I[VX])", numeroRomano.getRegexDiccionario().get("grupoSustractivo"));
    }

    @Test
    public void toDecimal() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().size());
        assertTrue(numeroRomano.getExpresionsValues().contains("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])"));
        assertTrue(numeroRomano.getExpresionsValues().contains("(C[DM])|(X[LC])|(I[VX])"));		
    }

    @Test
    public void valorDecimal_test() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().size());
        assertEquals((short)5, numeroRomano.valorDecimal(test));

        test = "IV"; 
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)4, numeroRomano.valorDecimal(test));

        test = "CM"; 
        numeroRomano.setNumeroRomano(test);
        assertEquals((short)900, numeroRomano.valorDecimal(test));

        /**
         *  test = "U";
         * numeroRomano.setNumeroRomano("U");
         * assertEquals(900, numeroRomano.valorDecimal(test));
         */
    }
}