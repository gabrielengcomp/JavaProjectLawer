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
import dtos.PessoaJuridicaDto;
import exceptions.CnpjException;
import exceptions.CpfException;
import exceptions.EmailException;
import exceptions.PessoaException;

public class PessoaJuridicaView extends JFrame {

	private static final long serialVersionUID = -6468157264434224542L;

	private PessoaController pessoaController;

	private JLabel lblNome;
	private JTextField txtNome;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblCnpj;
	private JTextField txtCnpj;

	private JLabel lblTelefone;
	private JTextField txtTelefone;

	private JLabel lblCpfPreposto;
	private JTextField txtCpfPreposto;

	private JButton btnBuscar;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnListar;
	private JButton btnRemover;

	private JTextArea txtLista;

	public PessoaJuridicaView(PessoaController pessoaController) {
		this.pessoaController = pessoaController;
		initialize();
	}

	private void initialize() {
		setTitle("Cadastro de Pessoa Física");
		setSize(900, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		JPanel pnlCnpj = new JPanel();
		lblCnpj = new JLabel("CNPJ");
		txtCnpj = new JTextField(20);
		btnBuscar = new JButton("Buscar");
		pnlCnpj.add(lblCnpj);
		pnlCnpj.add(txtCnpj);
		pnlCnpj.add(btnBuscar);

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

		JPanel pnlCpfPreposto = new JPanel();
		lblCpfPreposto = new JLabel("Cpf Preposto");
		txtCpfPreposto = new JTextField(20);
		pnlCpfPreposto.add(lblCpfPreposto);
		pnlCpfPreposto.add(txtCpfPreposto);

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

		add(pnlCnpj);
		add(pnlNome);
		add(pnlEmail);
		add(pnlTelefone);
		add(pnlCpfPreposto);
		add(pnlBotoes);
		add(pnlLista);
	}

	private void actionIncluir() {
		PessoaJuridicaDto dto = getDadosFormulario();
		try {
			pessoaController.createPessoaJuridica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa Juridica cadastrada com sucesso!");
			clear();
		} catch (PessoaException | CnpjException | EmailException | CpfException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		actionListar();
	}

	private void actionAlterar() {
		PessoaJuridicaDto dto = getDadosFormulario();
		try {
			pessoaController.updatePessoaJuridica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa atualizada com sucesso!");
			clear();
		} catch (PessoaException | EmailException | CpfException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		actionListar();
	}

	private void actionBuscar() {
		String cpf = txtCnpj.getText();
		try {
			PessoaJuridicaDto dto = pessoaController.getPessoaJuridica(cpf);
			txtNome.setText(dto.getNome());
			txtEmail.setText(dto.getEmail());
			txtTelefone.setText(dto.getTelefone());
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}

	private void actionRemover() {
		String cnpj = txtCnpj.getText();

		try {
			pessoaController.getPessoaJuridica(cnpj);
			pessoaController.removePessoaJuridica(cnpj);
			JOptionPane.showMessageDialog(this, "Pessoa Juridica removida com sucesso!");
			clear();
			actionListar();
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao remover Pessoa Juridica: " + e.getMessage());
		}
	}

	private void actionListar() {
		List<PessoaJuridicaDto> lista = pessoaController.getPessoasJuridicas();
		txtLista.setText("");
		for (PessoaJuridicaDto dto : lista) {
			txtLista.append(
					dto.getCnpj() + "\t" + dto.getNome() + "\t" + dto.getEmail() + "\t" + dto.getTelefone() + "\n");
		}
	}

	private PessoaJuridicaDto getDadosFormulario() {
		String nome = txtNome.getText();
		String email = txtEmail.getText();
		String cnpj = txtCnpj.getText();
		String telefone = txtTelefone.getText();
		String preposto = txtCpfPreposto.getText();
		return new PessoaJuridicaDto(nome, email, cnpj, telefone, preposto);
	}

	private void clear() {
		txtNome.setText("");
		txtEmail.setText("");
		txtCnpj.setText("");
		txtTelefone.setText("");
		txtCpfPreposto.setText("");
	}
}
