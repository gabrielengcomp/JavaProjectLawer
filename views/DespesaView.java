package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.ProcessoController;
import dtos.DespesaDto;
import exceptions.DespesaException;
import exceptions.ProcessoException;

public class DespesaView extends JFrame {

	private static final long serialVersionUID = -5645849483008369562L;
	private ProcessoController processoController;

	private JLabel lblNumProcesso;
	private JTextField txtNumProcesso;
	private JButton btnListarDespesas;

	private JLabel lblData;
	private JTextField txtData;

	private JLabel lblDescricao;
	private JTextField txtDescricao;

	private JLabel lblValor;
	private JTextField txtValor;

	private JButton btnIncluir;
	private JButton btnCancelar;

	private JTextArea txtListaDespesas;
	private JScrollPane scrollPane;

	public DespesaView(ProcessoController processoController) {
		this.processoController = processoController;
		initialize();
	}

	private void initialize() {
	    this.setTitle("Cadastro de Despesas");
	    this.setSize(600, 500); 
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setLayout(new BorderLayout(10, 10)); 

	    JPanel pnlCampos = new JPanel();
	    pnlCampos.setLayout(new GridLayout(4, 1, 5, 5));

	    JPanel pnlProcesso = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    lblNumProcesso = new JLabel("Número do Processo:");
	    txtNumProcesso = new JTextField(20);
	    btnListarDespesas = new JButton("Listar Despesas");
	    pnlProcesso.add(lblNumProcesso);
	    pnlProcesso.add(txtNumProcesso);
	    pnlProcesso.add(btnListarDespesas);
	    pnlCampos.add(pnlProcesso);

	    JPanel pnlData = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    lblData = new JLabel("Data (dd/MM/yyyy):");
	    txtData = new JTextField(15);
	    pnlData.add(lblData);
	    pnlData.add(txtData);
	    pnlCampos.add(pnlData);

	    JPanel pnlDescricao = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    lblDescricao = new JLabel("Descrição:");
	    txtDescricao = new JTextField(30);
	    pnlDescricao.add(lblDescricao);
	    pnlDescricao.add(txtDescricao);
	    pnlCampos.add(pnlDescricao);

	    JPanel pnlValor = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    lblValor = new JLabel("Valor R$:");
	    txtValor = new JTextField(15);
	    pnlValor.add(lblValor);
	    pnlValor.add(txtValor);
	    pnlCampos.add(pnlValor);

	    this.add(pnlCampos, BorderLayout.NORTH);

	    txtListaDespesas = new JTextArea(15, 40);
	    txtListaDespesas.setEditable(false);
	    scrollPane = new JScrollPane(txtListaDespesas);
	    this.add(scrollPane, BorderLayout.CENTER);

	    JPanel pnlBotoes = new JPanel();
	    btnIncluir = new JButton("Incluir Despesa");
	    btnCancelar = new JButton("Cancelar");
	    pnlBotoes.add(btnIncluir);
	    pnlBotoes.add(btnCancelar);
	    this.add(pnlBotoes, BorderLayout.SOUTH);

		btnListarDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarDespesas();
			}
		});

		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionIncluirDespesa();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});
	}

	private void actionListarDespesas() {
		String numero = txtNumProcesso.getText();

		try {
			String despesas = processoController.listarCustas(numero);
			Double total = processoController.totalCustas(numero);
			txtListaDespesas.setText("Despesas Totais (R$): " + total + "\n" + despesas);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao listar despesas: " + e.getMessage());
			txtListaDespesas.setText("");
		}
	}

	private void actionIncluirDespesa() {
		String numProcesso = txtNumProcesso.getText();

		Date data;
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText().trim());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy");
			return;
		}

		String descricao = txtDescricao.getText();
		double valor = Double.parseDouble(txtValor.getText());

		DespesaDto dto = new DespesaDto(numProcesso, data, descricao, valor);

		try {
			processoController.addDespesa(dto);
			JOptionPane.showMessageDialog(this, "Despesa cadastrada com sucesso!");
			clear();
			actionListarDespesas();
		} catch (ProcessoException | DespesaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar despesa: " + e.getMessage());
		}
	}

	private void actionCancelar() {
		clear();
	}

	private void clear() {
		txtData.setText("");
		txtDescricao.setText("");
		txtValor.setText("");
		txtListaDespesas.setText("");
	}
}