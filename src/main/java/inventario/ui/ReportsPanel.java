package inventario.ui;

import inventario.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class ReportsPanel extends JPanel {
    private final MainController controller;

    private JTextField txtFrom;
    private JTextField txtTo;
    private JButton btnGenerate;
    private JTextArea txtReport;

    public ReportsPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = createFormPanel();
        add(form, BorderLayout.NORTH);

        txtReport = new JTextArea();
        txtReport.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtReport.setEditable(false);
        txtReport.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(txtReport);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));

        txtFrom = createStyledTextField(10);
        txtTo = createStyledTextField(10);

        btnGenerate = createStyledButton("Generar Reporte", new Color(70, 130, 180));

        form.add(createFormLabel("Desde (YYYY-MM-DD):"));
        form.add(txtFrom);
        form.add(createFormLabel("Hasta (YYYY-MM-DD):"));
        form.add(txtTo);
        form.add(btnGenerate);

        return form;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker()),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        return button;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private JTextField createStyledTextField(int i) {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void generate() {
        try {
            LocalDate from = LocalDate.parse(txtFrom.getText());
            LocalDate to = LocalDate.parse(txtTo.getText());
            Map<LocalDate, Double> report = controller.getProfitReport(from, to);
            StringBuilder sb = new StringBuilder();
            report.forEach((date, profit) -> sb.append(date).append(" = ").append(profit).append("\n"));
            txtReport.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generando reporte: " + ex.getMessage());
        }
    }
}

