package numerosRomanos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroRomano {

	private String numeroRomano;
	private short numeroDecimal = 0;

	private Map<String, String> regexDiccionario = new HashMap<String, String>();



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

	public Map<String, String> getRegexDiccionario() {
		return regexDiccionario;
	}

	public void setRegexDiccionario(Map<String, String> regexDiccionario) {
		this.regexDiccionario = regexDiccionario;
	}
	public void initRegexDicionario() {
		this.regexDiccionario.put("sumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
		this.regexDiccionario.put("substractivo", "(C[DM])|(X[LC])|(I[VX])");
	}

	public Object toDecimal() {
		for(String regex : getExpresionsValues()){
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.numeroRomano);
			separarYSumar(matcher);}
		return getNumeroDecimal();
		
	}

	private void separarYSumar(Matcher matcher) {
		while (matcher.find()) {
				this.numeroDecimal += valorDecimal(matcher.group());
		}
	}

	public short valorDecimal(String numeroRomano) {
		ValorDecimanal posicionNumerica = Enum.valueOf(ValorDecimanal.class, String.valueOf(numeroRomano));
		return (short) posicionNumerica.getValorDecimal();
	}


	public List<String> getExpresionsValues() {
		List<String> valuesList = new ArrayList<>();
		this.regexDiccionario.forEach((k,v) -> valuesList.add(v));
		return valuesList;
	}


}



