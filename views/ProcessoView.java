package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.ProcessoController;
import dtos.ProcessoDto;
import enumarations.EFaseProcesso;
import exceptions.PessoaException;
import exceptions.ProcessoException;
import exceptions.TribunalException;

public class ProcessoView extends JFrame {

	private static final long serialVersionUID = -5609029755590085725L;

	private ProcessoController processoController;

	private JLabel lblNumero;
	private JTextField txtNumero;
	private JButton btnBuscar;

	private JLabel lblData;
	private JTextField txtData;

	private JLabel lblFase;
	private JTextField txtFase;

	private JLabel lblTribunal;
	private JTextField txtTribunal;

	private JLabel lblCliente;
	private JTextField txtCliente;

	private JLabel lblParteContraria;
	private JTextField txtParteContraria;

	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnCancelar;
	private JButton btnRemover;
	private JButton btnListar;
	private JButton btnListarRF;

	private JTextArea txtLista;

	public ProcessoView(ProcessoController processoController) {
		this.processoController = processoController;
		initialize();
	}

	private void initialize() {
		this.setTitle("Gerenciador de Processos");
		this.setSize(750, 600); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10)); 

		JPanel pnlFormulario = new JPanel(new GridLayout(3, 2, 10, 10)); 
		pnlFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel pnlNumero = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblNumero = new JLabel("Número:");
		txtNumero = new JTextField(15);
		btnBuscar = new JButton("Buscar");
		pnlNumero.add(lblNumero);
		pnlNumero.add(txtNumero);
		pnlNumero.add(btnBuscar);
		pnlFormulario.add(pnlNumero);

		JPanel pnlData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblData = new JLabel("Data (dd/MM/yyyy):");
		txtData = new JTextField(15);
		pnlData.add(lblData);
		pnlData.add(txtData);
		pnlFormulario.add(pnlData);

		JPanel pnlFase = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblFase = new JLabel("Fase:");
		txtFase = new JTextField(15);
		pnlFase.add(lblFase);
		pnlFase.add(txtFase);
		pnlFormulario.add(pnlFase);

		JPanel pnlTribunal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblTribunal = new JLabel("Tribunal (Sigla):");
		txtTribunal = new JTextField(15);
		pnlTribunal.add(lblTribunal);
		pnlTribunal.add(txtTribunal);
		pnlFormulario.add(pnlTribunal);

		JPanel pnlCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblCliente = new JLabel("Cliente (RF):");
		txtCliente = new JTextField(15);
		btnListarRF = new JButton("Listar");
		pnlCliente.add(lblCliente);
		pnlCliente.add(txtCliente);
		pnlCliente.add(btnListarRF);
		pnlFormulario.add(pnlCliente);

		JPanel pnlParteContraria = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblParteContraria = new JLabel("Parte Contrária (RF):");
		txtParteContraria = new JTextField(15);
		pnlParteContraria.add(lblParteContraria);
		pnlParteContraria.add(txtParteContraria);
		pnlFormulario.add(pnlParteContraria);

		this.add(pnlFormulario, BorderLayout.NORTH);

		JPanel pnlLista = new JPanel(new BorderLayout(10, 10));
		pnlLista.setBorder(BorderFactory.createTitledBorder("Lista de Processos"));
		txtLista = new JTextArea(15, 50);
		txtLista.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtLista);
		pnlLista.add(scrollPane, BorderLayout.CENTER);

		JPanel pnlListaBotoes = new JPanel();
		btnListar = new JButton("Listar Todos");
		pnlListaBotoes.add(btnListar);
		pnlLista.add(pnlListaBotoes, BorderLayout.SOUTH);

		this.add(pnlLista, BorderLayout.CENTER);

		JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		pnlBotoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		btnIncluir = new JButton("Incluir");
		btnAlterar = new JButton("Alterar");
		btnCancelar = new JButton("Cancelar");
		btnRemover = new JButton("Remover");
		pnlBotoes.add(btnIncluir);
		pnlBotoes.add(btnAlterar);
		pnlBotoes.add(btnCancelar);
		pnlBotoes.add(btnRemover);
		this.add(pnlBotoes, BorderLayout.SOUTH);

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

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRemover();
			}
		});

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});

		btnListarRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarRF();
			}
		});

	}

	private void actionBuscar() {
		String numero = txtNumero.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			ProcessoDto dto = processoController.getProcesso(numero);

			txtNumero.setText(dto.getNumero());
			txtData.setText(sdf.format(dto.getDataAbertura()));
			txtFase.setText(dto.getFase().name());
			txtTribunal.setText(dto.getTribunal());
			txtCliente.setText(dto.getCliente());
			txtParteContraria.setText(dto.getParteContraria());

		} catch (ProcessoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}

	private void actionIncluir() {
		String numero = txtNumero.getText();
		Date data;
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy");
			return;
		}

		EFaseProcesso fase;
		try {
			fase = EFaseProcesso.valueOf(txtFase.getText());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Fase inválida!");
			return;
		}

		String tribunal = txtTribunal.getText();
		String cliente = txtCliente.getText();
		String parteContraria = txtParteContraria.getText();

		ProcessoDto dto = new ProcessoDto(numero, data, fase, tribunal, cliente, parteContraria);

		try {
			processoController.createProcesso(dto);
			JOptionPane.showMessageDialog(this, "Processo incluído com sucesso!");
			clear();
			actionListar();
		} catch (ProcessoException | TribunalException | PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void actionAlterar() {
		String numero = txtNumero.getText();
		Date data;
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy");
			return;
		}

		EFaseProcesso fase;
		try {
			fase = EFaseProcesso.valueOf(txtFase.getText());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Fase inválida!");
			return;
		}

		String tribunal = txtTribunal.getText();
		String cliente = txtCliente.getText();
		String parteContraria = txtParteContraria.getText();

		ProcessoDto dto = new ProcessoDto(numero, data, fase, tribunal, cliente, parteContraria);

		try {
			processoController.updateProcesso(dto);
			JOptionPane.showMessageDialog(this, "Processo alterado com sucesso!");
			clear();
			actionListar();
		} catch (ProcessoException | TribunalException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void actionRemover() {
		String numero = txtNumero.getText();

		try {
			processoController.getProcesso(numero);
			processoController.removeProcesso(numero);
			JOptionPane.showMessageDialog(this, "Processo removido com sucesso!");
			clear();
			actionListar();
		} catch (ProcessoException e) {
			JOptionPane.showMessageDialog(this, "Erro ao remover processo: " + e.getMessage());

		}
	}

	private void actionListarRF() {
		String cadastroRF = txtCliente.getText();
		List<ProcessoDto> lista;
		try {
			lista = processoController.getProcessosRF(cadastroRF);

			txtLista.setText("");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (ProcessoDto dto : lista) {
				txtLista.append(
						String.format("%s\t%s\t%s\t%s\t%s\t%s\n", dto.getNumero(), sdf.format(dto.getDataAbertura()),
								dto.getTribunal(), dto.getFase(), dto.getCliente(), dto.getParteContraria()));
			}
		} catch (ProcessoException e) {
			JOptionPane.showMessageDialog(this, "Erro ao buscar processos: " + e.getMessage());
		}
	}

	private void actionListar() {
		List<ProcessoDto> lista = processoController.getProcessos();
		txtLista.setText("");

		if (lista.isEmpty()) {
			txtLista.setText("Nenhum processo cadastrado.");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (ProcessoDto dto : lista) {
			txtLista.append(
					String.format("%s\t%s\t%s\t%s\t%s\t%s\n", dto.getNumero(), sdf.format(dto.getDataAbertura()),
							dto.getTribunal(), dto.getFase(), dto.getCliente(), dto.getParteContraria()));
		}
	}

	private void actionCancelar() {
		clear();
	}

	private void clear() {
		txtNumero.setText("");
		txtData.setText("");
		txtFase.setText("");
		txtTribunal.setText("");
		txtCliente.setText("");
		txtParteContraria.setText("");
	}
}