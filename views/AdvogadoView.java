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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.PessoaController;
import dtos.AdvogadoDto;
import exceptions.AdvogadoException;
import exceptions.CpfException;
import exceptions.PessoaException;

public class AdvogadoView extends JFrame {

	private static final long serialVersionUID = -1860638680621357455L;

	private PessoaController pessoaController;

	private JLabel lblCpf, lblRegistro;
	private JTextField txtCpf, txtRegistro;

	private JButton btnBuscar, btnIncluir, btnCancelar, btnListar;
	private JTextArea txtLista;

	public AdvogadoView(PessoaController pessoaController) {
		this.pessoaController = pessoaController;
		initialize();
	}

	private void initialize() {
		setTitle("Cadastro de Advogado");
		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// CPF
		JPanel pnlCpf = new JPanel();
		lblCpf = new JLabel("CPF");
		txtCpf = new JTextField(15);
		btnBuscar = new JButton("Buscar");
		pnlCpf.add(lblCpf);
		pnlCpf.add(txtCpf);

		// Registro
		JPanel pnlRegistro = new JPanel();
		lblRegistro = new JLabel("Registro");
		txtRegistro = new JTextField(15);
		pnlRegistro.add(lblRegistro);
		pnlRegistro.add(txtRegistro);
		pnlRegistro.add(btnBuscar);

		// Botões
		JPanel pnlBotoes = new JPanel();
		btnIncluir = new JButton("Incluir");
		btnCancelar = new JButton("Cancelar");
		pnlBotoes.add(btnIncluir);
		pnlBotoes.add(btnCancelar);

		// Lista
		JPanel pnlLista = new JPanel();
		txtLista = new JTextArea(10, 60);
		txtLista.setEnabled(false);
		btnListar = new JButton("Listar");
		pnlLista.add(new JScrollPane(txtLista));
		pnlLista.add(btnListar);

		// Eventos
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

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});

		// Adicionando ao frame
		add(pnlRegistro);
		add(pnlCpf);
		add(pnlBotoes);
		add(pnlLista);
	}

	private void actionBuscar() {
		String registro = txtRegistro.getText();
		try {
			AdvogadoDto dto = pessoaController.getAdvogado(registro);
			txtCpf.setText(dto.getCpf());
		} catch (AdvogadoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}

	private void actionIncluir() {
		AdvogadoDto dto = getDadosFormulario();
		try {
			pessoaController.createAdvogado(dto);
			JOptionPane.showMessageDialog(this, "Advogado cadastrado com sucesso!");
			clear();
		} catch (AdvogadoException | PessoaException | CpfException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		actionListar();
	}

	private void actionListar() {
		List<AdvogadoDto> lista = pessoaController.getAdvogados();
		txtLista.setText("");
		
		for (AdvogadoDto dto : lista) {
			txtLista.append(dto.getCpf() + "\t" + dto.getRegistro() + "\t" + dto.getNome() + "\t" + dto.getEmail()
					+ "\t" + dto.getTelefone() + "\n");
		}
	}

	private AdvogadoDto getDadosFormulario() {
		String cpf = txtCpf.getText();
		String registro = txtRegistro.getText();
		return new AdvogadoDto(registro, cpf, null, null, null);
	}

	private void clear() {
		txtCpf.setText("");
		txtRegistro.setText("");
	}
}
