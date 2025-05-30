package main;
//Gabriel Henrique Pires dos Santos
import java.util.Date;

import domain.Advogado;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Processo;
import domain.Tribunal;
import enumarations.EFaseProcesso;

public class TesteAdvocacia {

	public static void main(String[] args) {
		Date date1 = new Date();
		
		PessoaFisica pf2 = new PessoaFisica("Gabriel", "emailGabriel@gmail", 21999999, 456789101);
		PessoaFisica pf3 = new PessoaFisica("Ana", "emailAna@gmail", 219977777, 842383301);

		
		Advogado adv1 = new Advogado("Joao", "emailjoao@gmail", 21988888, 987654221, 123);
		
		PessoaJuridica pj1 = new PessoaJuridica("Dani", "emailDani@gmail", 21999555, 42342433, pf2);
	
		Tribunal t1 = new Tribunal("TVF", "Tribunal da Vara Familiar", "Vara Familiar");
		Tribunal t2 = new Tribunal("TVC", "Tribunal da Vara Criminal", "Vara Criminal");

		Processo p1 = new Processo(100);
		
		p1.setCliente(pf2);
		p1.setParteContraria(pf3);
		p1.setTribunal(t1);
		p1.addAudiencia(date1, "Recomendacao1", adv1);
		p1.addAudiencia(date1, "Recomendacao2", adv1);
		p1.addDespesa(date1, "Xerox", 200.00);
		p1.addDespesa(date1, "Viagem1", 500.00);
		
		System.out.println(p1.toString());
		System.out.println("Total Custas: " + p1.getTotalCustas());
		System.out.println(p1.getCustas());
		System.out.println(p1.getAudiencias());
		
		System.out.println("---------------------------------------------");
		
		p1.setFase(EFaseProcesso.DECISAO);
		p1.addAudiencia(date1, "Recomendacao3", adv1);
		p1.addDespesa(date1, "Viagem2", 300.00);
		System.out.println(p1.toString());
		System.out.println("Total Custas: " + p1.getTotalCustas());
		System.out.println(p1.getCustas());
		System.out.println(p1.getAudiencias());
	}

}
