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
import dtos.PessoaFisicaDto;
import exceptions.CpfException;
import exceptions.EmailException;
import exceptions.PessoaException;

public class PessoaFisicaView extends JFrame {

	private static final long serialVersionUID = -7139840682678893007L;

	private PessoaController pessoaController;

	private JLabel lblNome;
	private JTextField txtNome;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblCpf;
	private JTextField txtCpf;

	private JLabel lblTelefone;
	private JTextField txtTelefone;

	private JButton btnBuscar;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnListar;
	private JButton btnRemover;

	private JTextArea txtLista;

	public PessoaFisicaView(PessoaController pessoaController) {
		this.pessoaController = pessoaController;
		initialize();
	}

	private void initialize() {
		setTitle("Cadastro de Pessoa Física");
		setSize(900, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel pnlCpf = new JPanel();
		lblCpf = new JLabel("CPF");
		txtCpf = new JTextField(15);
		btnBuscar = new JButton("Buscar");
		pnlCpf.add(lblCpf);
		pnlCpf.add(txtCpf);
		pnlCpf.add(btnBuscar);

		JPanel pnlNome = new JPanel();
		lblNome = new JLabel("Nome");
		txtNome = new JTextField(30);
		pnlNome.add(lblNome);
		pnlNome.add(txtNome);

		JPanel pnlEmail = new JPanel();
		lblEmail = new JLabel("Email");
		txtEmail = new JTextField(25);
		pnlEmail.add(lblEmail);
		pnlEmail.add(txtEmail);

		JPanel pnlTelefone = new JPanel();
		lblTelefone = new JLabel("Telefone");
		txtTelefone = new JTextField(15);
		pnlTelefone.add(lblTelefone);
		pnlTelefone.add(txtTelefone);

		JPanel pnlBotoes = new JPanel();
		btnIncluir = new JButton("Incluir");
		btnAlterar = new JButton("Alterar");
		btnCancelar = new JButton("Cancelar");
		btnRemover = new JButton("Remover");
		pnlBotoes.add(btnIncluir);
		pnlBotoes.add(btnAlterar);
		pnlBotoes.add(btnCancelar);
		pnlBotoes.add(btnRemover);

		JPanel pnlLista = new JPanel();
		txtLista = new JTextArea(10, 60);
		txtLista.setEnabled(false);
		btnListar = new JButton("Listar");
		pnlLista.add(new JScrollPane(txtLista));
		pnlLista.add(btnListar);

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
				clear();
			}
		});
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRemover();
			}
		});

		add(pnlCpf);
		add(pnlNome);
		add(pnlEmail);
		add(pnlTelefone);
		add(pnlBotoes);
		add(pnlLista);
	}

	private void actionIncluir() {
		PessoaFisicaDto dto = getDadosFormulario();
		try {
			pessoaController.createPessoaFisica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");
			clear();
		} catch (PessoaException | CpfException | EmailException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		actionListar();
	}

	private void actionAlterar() {
		PessoaFisicaDto dto = getDadosFormulario();
		try {
			pessoaController.updatePessoaFisica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa atualizada com sucesso!");
			clear();
		} catch (PessoaException | EmailException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		actionListar();
	}

	private void actionBuscar() {
		String cpf = txtCpf.getText();
		try {
			PessoaFisicaDto dto = pessoaController.getPessoaFisica(cpf);
			txtNome.setText(dto.getNome());
			txtEmail.setText(dto.getEmail());
			txtTelefone.setText(dto.getTelefone());
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}

	private void actionRemover() {
		String cpf = txtCpf.getText();

		try {
			pessoaController.getPessoaFisica(cpf);
			pessoaController.removePessoaFisica(cpf);
			JOptionPane.showMessageDialog(this, "Pessoa Fisica removida com sucesso!");
			clear();
			actionListar();
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao remover Pessoa Fisica: " + e.getMessage());
		}
	}

	private void actionListar() {
		List<PessoaFisicaDto> lista = pessoaController.getPessoasFisicas();
		txtLista.setText("");
		for (PessoaFisicaDto dto : lista) {
			txtLista.append(
					dto.getCpf() + "\t" + dto.getNome() + "\t" + dto.getEmail() + "\t" + dto.getTelefone() + "\n");
		}
	}

	private PessoaFisicaDto getDadosFormulario() {
		String nome = txtNome.getText();
		String email = txtEmail.getText();
		String cpf = txtCpf.getText();
		String telefone = txtTelefone.getText();
		return new PessoaFisicaDto(nome, email, cpf, telefone);
	}

	private void clear() {
		txtNome.setText("");
		txtEmail.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
	}
}
