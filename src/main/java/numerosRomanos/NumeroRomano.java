package numerosRomanos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroRomano {

	private String numeroRomano;
	private short numeroDecimal = 0;

	private String regex;



	public NumeroRomano() {
	}

	public String getNumeroRomano() {
		return numeroRomano;
	}


	public void setNumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
		this.setNumeroDecimal((short) 0);
	}


	public short getNumeroDecimal() {
		return numeroDecimal;
	}


	public void setNumeroDecimal(short numeroDecimal) {
		this.numeroDecimal = numeroDecimal;
	};

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public void initRegexDicionario() {
		setRegex("C|M");
	}

	public Object toDecimal() {
		Pattern pattern = Pattern.compile(this.regex);
		Matcher matcher = pattern.matcher(this.numeroRomano);
		separarYSumar(matcher);
		return getNumeroDecimal();
		
	}

	private void separarYSumar(Matcher matcher) {
		while (matcher.find()) {
				this.numeroDecimal += valorDecimal(matcher.group());
				System.out.println(matcher.group());

		}
	}

	public short valorDecimal(String numeroRomano) {
		ValorDecimanal posicionNumerica = Enum.valueOf(ValorDecimanal.class, String.valueOf(numeroRomano));
		return (short) posicionNumerica.getValorDecimal();
	}


}



