package views;

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
import dtos.AudienciaDto;
import exceptions.AdvogadoException;
import exceptions.AudienciaException;
import exceptions.ProcessoException;

public class AudienciaView extends JFrame {

	private static final long serialVersionUID = -1553308439372602490L;

	private ProcessoController processoController;

	private JLabel lblNumProcesso;
	private JTextField txtNumProcesso;
	private JButton btnListarAudiencias;

	private JLabel lblData;
	private JTextField txtData;

	private JLabel lblRecomendacao;
	private JTextField txtRecomendacao;

	private JLabel lblAdvogado;
	private JTextField txtAdvogado;

	private JButton btnIncluir;
	private JButton btnCancelar;

	private JTextArea txtListaAudiencias;
	private JScrollPane scrollPane;

	public AudienciaView(ProcessoController processoController) {
		this.processoController = processoController;
		initialize();
	}

	private void initialize() {
		this.setTitle("Cadastro de Audiências");
		this.setSize(500, 400); 
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

		JPanel pnlProcesso = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblNumProcesso = new JLabel("Número do Processo:");
		txtNumProcesso = new JTextField(15);
		btnListarAudiencias = new JButton("Listar Audiências");
		pnlProcesso.add(lblNumProcesso);
		pnlProcesso.add(txtNumProcesso);
		pnlProcesso.add(btnListarAudiencias);
		this.add(pnlProcesso);

		JPanel pnlCadastro = new JPanel();
		pnlCadastro.setLayout(new GridLayout(3, 2, 5, 5)); 

		lblData = new JLabel("Data (dd/MM/yyyy):");
		txtData = new JTextField();
		pnlCadastro.add(lblData);
		pnlCadastro.add(txtData);

		lblRecomendacao = new JLabel("Recomendação:");
		txtRecomendacao = new JTextField();
		pnlCadastro.add(lblRecomendacao);
		pnlCadastro.add(txtRecomendacao);

		lblAdvogado = new JLabel("Registro Advogado:");
		txtAdvogado = new JTextField();
		pnlCadastro.add(lblAdvogado);
		pnlCadastro.add(txtAdvogado);

		this.add(pnlCadastro);

		JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		btnIncluir = new JButton("Incluir Audiência");
		btnCancelar = new JButton("Cancelar");
		pnlBotoes.add(btnIncluir);
		pnlBotoes.add(btnCancelar);
		this.add(pnlBotoes);

		txtListaAudiencias = new JTextArea(8, 40);
		txtListaAudiencias.setEditable(false);
		scrollPane = new JScrollPane(txtListaAudiencias);
		this.add(scrollPane);

		btnListarAudiencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarAudiencias();
			}
		});

		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionIncluirAudiencia();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});
	}

	private void actionListarAudiencias() {
		String numero = txtNumProcesso.getText();

		try {
			String audiencias = processoController.listarAudiencias(numero);

			if (audiencias.isEmpty()) {
				txtListaAudiencias.setText("Nenhuma audiência cadastrada para este processo.");
			} else {
				txtListaAudiencias.setText(audiencias);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao listar audiências: " + e.getMessage());
			txtListaAudiencias.setText("");
		}
	}

	private void actionIncluirAudiencia() {
		String numProcesso = txtNumProcesso.getText();

		Date data;
		try {
			String dataStr = txtData.getText().trim();
			data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy");
			return;
		}

		String recomendacao = txtRecomendacao.getText();
		String advogadoRegistro = txtAdvogado.getText();

		AudienciaDto dto = new AudienciaDto(numProcesso, data, recomendacao, advogadoRegistro);

		try {
			processoController.addAudiencia(dto);
			JOptionPane.showMessageDialog(this, "Audiência cadastrada com sucesso!");
			clear();
			actionListarAudiencias();
		} catch (ProcessoException | AudienciaException | AdvogadoException e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar audiência: " + e.getMessage());
		}
	}

	private void actionCancelar() {
		clear();
	}

	private void clear() {
		txtData.setText("");
		txtRecomendacao.setText("");
		txtAdvogado.setText("");
		txtListaAudiencias.setText("");
	}
}
