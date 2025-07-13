package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controllers.MainController;

public class MenuView extends JFrame {

	private static final long serialVersionUID = -6778634079898677280L;

	private JButton btnTribunal;
	private JButton btnPessoaFisica;
	private JButton btnPessoaJuridica;
	private JButton btnAdvogado;
	private JButton btnProcesso;
	private JButton btnDespesa;
	private JButton btnAudiencia;

	public MenuView() {
		initialize();
	}

	private void initialize() {

		this.setTitle("Sistem de Gestão de Processos");
		this.setSize(500, 400);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnTribunal = new JButton("Cadastrar Tribunal");
		btnTribunal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunal();
			}
		});

		btnPessoaFisica = new JButton("Cadastrar PF");
		btnPessoaFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPessoaFisica();
			}
		});

		btnPessoaJuridica = new JButton("Cadastrar PJ");
		btnPessoaJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPessoaJuridica();
			}
		});

		btnAdvogado = new JButton("Cadastrar Advogado");
		btnAdvogado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAdvogado();
			}
		});

		btnProcesso = new JButton("Cadastrar Processo");
		btnProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionProcesso();
			}
		});

		btnDespesa = new JButton("Gerenciar Despesas");
		btnDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDespesa();
			}
		});

		btnAudiencia = new JButton("Gerenciar Audiencias");
		btnAudiencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAudiencia();
			}
		});

		this.add(btnTribunal);
		this.add(btnPessoaFisica);
		this.add(btnPessoaJuridica);
		this.add(btnAdvogado);
		this.add(btnProcesso);
		this.add(btnDespesa);
		this.add(btnAudiencia);
	}

	private void actionTribunal() {

		TribunalView tribunalView = new TribunalView(MainController.getTribunalController());
		tribunalView.setVisible(true);
	}

	private void actionPessoaFisica() {

		PessoaFisicaView pessoaFisicaView = new PessoaFisicaView(MainController.getPessoaController());
		pessoaFisicaView.setVisible(true);
	}

	private void actionPessoaJuridica() {
		PessoaJuridicaView pessoaJuridicaView = new PessoaJuridicaView(MainController.getPessoaController());
		pessoaJuridicaView.setVisible(true);
	}

	private void actionAdvogado() {
		AdvogadoView advogadoView = new AdvogadoView(MainController.getPessoaController());
		advogadoView.setVisible(true);
	}

	private void actionProcesso() {
		ProcessoView processoView = new ProcessoView(MainController.getProcessoController());
		processoView.setVisible(true);
	}

	private void actionDespesa() {
		DespesaView despesaView = new DespesaView(MainController.getProcessoController());
		despesaView.setVisible(true);
	}
	
	private void actionAudiencia() {
		AudienciaView audienciaView = new AudienciaView(MainController.getProcessoController());
		audienciaView.setVisible(true);
	}
}