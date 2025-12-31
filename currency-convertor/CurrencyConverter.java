import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;

public class CurrencyConverter extends JFrame {

    private JComboBox<String> baseC;
    private JComboBox<String> tarC;
    private JTextField amtF;
    private JLabel resL;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 8, 8));

        String[] c = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD"};

        baseC = new JComboBox<>(c);
        tarC = new JComboBox<>(c);
        amtF = new JTextField();

        JButton btn = new JButton("Convert");
        resL = new JLabel("Converted Value Will Appear Here", SwingConstants.CENTER);

        add(new JLabel("Select Base Currency:"));
        add(baseC);
        add(new JLabel("Select Target Currency:"));
        add(tarC);
        add(new JLabel("Enter Amount:"));
        add(amtF);
        add(btn);
        add(resL);

        btn.addActionListener(e -> convert());
    }

    private void convert() {
        try {
            String b = baseC.getSelectedItem().toString();
            String t = tarC.getSelectedItem().toString();
            double amt = Double.parseDouble(amtF.getText());

            if (b.equals(t)) {
                resL.setText(amt + " " + b + " = " + amt + " " + t);
                return;
            }

            String url = "https://api.frankfurter.app/latest?amount=" + amt + "&from=" + b + "&to=" + t;

            HttpClient c = HttpClient.newHttpClient();
            HttpRequest r = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> res = c.send(r, HttpResponse.BodyHandlers.ofString());

            String json = res.body();
            String part = json.split("\"" + t + "\":")[1];
            part = part.split("}")[0];
            double out = Double.parseDouble(part);

            resL.setText(String.format("%.2f %s = %.2f %s", amt, b, out, t));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Something went wrong. Maybe internet or input is bad.",
                    "Oops",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter().setVisible(true));
    }
}
