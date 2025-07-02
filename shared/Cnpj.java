package shared;

import java.io.Serializable;

import exceptions.CnpjException;

public class Cnpj implements Serializable {

	private static final long serialVersionUID = 3441977925616801321L;
	private final String cnpj;

	public Cnpj(String cnpj) throws CnpjException {

		if (validaCnpj(cnpj)) {
			this.cnpj = cnpj;
		} else {
			throw new CnpjException("Cnpj inválido");
		}
	}

	public boolean validaCnpj(String cnpj) {
		cnpj = cnpj.replaceAll("[^\\d]", ""); // remove pontuação

		if (cnpj.length() != 14 || cnpj.chars().distinct().count() == 1)
			return false;

		try {
			int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
			int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

			int soma = 0;
			for (int i = 0; i < 12; i++)
				soma += (cnpj.charAt(i) - '0') * pesos1[i];
			int dig13 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

			soma = 0;
			for (int i = 0; i < 13; i++)
				soma += (cnpj.charAt(i) - '0') * pesos2[i];
			int dig14 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

			return (cnpj.charAt(12) - '0') == dig13 && (cnpj.charAt(13) - '0') == dig14;
		} catch (Exception e) {
			return false;
		}
	}

	public String getCnpj() {
		return cnpj;
	}
}
