package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.TribunalController;
import dtos.TribunalDto;
import exceptions.TribunalException;

public class TribunalView extends JFrame {

	private static final long serialVersionUID = -3968137772129990235L;

	private TribunalController tribunalController;

	private JLabel lblSigla;
	private JTextField txtSigla;

	private JLabel lblNome;
	private JTextField txtNome;

	private JLabel lblSecao;
	private JTextField txtSecao;

	private JButton btnBuscar;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnListar;

	private JTextArea txtLista;

	public TribunalView(TribunalController tribunalController) {
		this.tribunalController = tribunalController;
		initialize();
	}

	private void initialize() {

		this.setTitle("Cadastro de Tribunal");
		this.setSize(700, 400);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel pnlSigla = new JPanel();
		lblSigla = new JLabel("Sigla");
		txtSigla = new JTextField(10);
		btnBuscar = new JButton("Buscar");

		pnlSigla.add(lblSigla);
		pnlSigla.add(txtSigla);
		pnlSigla.add(btnBuscar);

		JPanel pnlNome = new JPanel();
		lblNome = new JLabel("Nome");
		txtNome = new JTextField(35);

		pnlNome.add(lblNome);
		pnlNome.add(txtNome);

		JPanel pnlSecao = new JPanel();
		lblSecao = new JLabel("Seção");
		txtSecao = new JTextField(20);

		pnlSecao.add(lblSecao);
		pnlSecao.add(txtSecao);

		JPanel pnlBotoes = new JPanel();
		btnIncluir = new JButton("Incluir");
		btnAlterar = new JButton("Alterar");
		btnCancelar = new JButton("Cancelar");

		pnlBotoes.add(btnIncluir);
		pnlBotoes.add(btnAlterar);
		pnlBotoes.add(btnCancelar);

		JPanel pnlListar = new JPanel();
		txtLista = new JTextArea(10, 40);
		txtLista.setEnabled(false);

		btnListar = new JButton("Listar");

		pnlListar.add(txtLista);
		pnlListar.add(btnListar);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscar();
			}
		});

		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionIncluir();
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAlterar();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});

		this.add(pnlSigla);
		this.add(pnlNome);
		this.add(pnlSecao);
		this.add(pnlBotoes);
		this.add(pnlListar);
	}

	private void actionIncluir() {

		String sigla;
		String nome;
		String secao;

		sigla = txtSigla.getText();
		nome = txtNome.getText();
		secao = txtSecao.getText();

		TribunalDto dto = new TribunalDto(sigla, nome, secao);

		try {
			tribunalController.createTribunal(dto);
			JOptionPane.showMessageDialog(this, "Tribunal gravado com sucesso!");
			clear();
		} catch (TribunalException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		actionListar();
	}

	private void actionAlterar() {

		String sigla;
		String nome;
		String secao;

		sigla = txtSigla.getText();
		nome = txtNome.getText();
		secao = txtSecao.getText();

		TribunalDto dto = new TribunalDto(sigla, nome, secao);

		try {
			tribunalController.updateTribunal(dto);
			JOptionPane.showMessageDialog(this, "Tribunal atualizado com sucesso!");
			clear();
		} catch (TribunalException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		actionListar();
	}

	private void actionBuscar() {

		String sigla;
		TribunalDto dto;

		sigla = txtSigla.getText();

		try {
			dto = tribunalController.getTribunal(sigla);

			txtSigla.setText(sigla);
			txtNome.setText(dto.getNome());
			txtSecao.setText(dto.getSecao());

		} catch (TribunalException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}

	private void actionCancelar() {
		clear();
	}

	private void actionListar() {

		List<TribunalDto> lista = tribunalController.getTribunais();

		txtLista.setText("");

		for (TribunalDto dto : lista) {
			txtLista.append(dto.getSigla() + "\t" + dto.getNome() + "\t" + dto.getSecao() + "\n");
		}
	}

	private void clear() {
		txtSigla.setText("");
		txtNome.setText("");
		txtSecao.setText("");
	}

}