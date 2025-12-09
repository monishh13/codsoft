import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Game extends JFrame {

    private int num, tries, time, score = 0;
    private Timer t;
    private final Random r = new Random();

    private JLabel msg = new JLabel("Choose difficulty to start", SwingConstants.CENTER);
    private final JLabel tr = new JLabel("", SwingConstants.CENTER);
    private JLabel tm = new JLabel("", SwingConstants.CENTER);
    private JTextField in = new JTextField();
    private JButton chk = new JButton("Check");
    private JButton rst = new JButton("Restart");
    private JPanel p = new JPanel(new GridLayout(6, 1));

    public Game() {
        setTitle("Number Guess Game");
        setSize(350, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton e = new JButton("Easy");
        JButton m = new JButton("Medium");
        JButton h = new JButton("Hard");

        JPanel lvl = new JPanel();
        lvl.add(e);
        lvl.add(m);
        lvl.add(h);

        p.add(msg);
        p.add(lvl);
        p.add(in);
        p.add(chk);
        p.add(tr);
        p.add(tm);
        add(p);

        chk.setEnabled(false);
        in.setEnabled(false);

        e.addActionListener(a -> start(1));
        m.addActionListener(a -> start(2));
        h.addActionListener(a -> start(3));

        chk.addActionListener(a -> check());
        rst.addActionListener(a -> reset());
    }

    private void start(int lv) {
        if (lv == 1) { num = r.nextInt(50) + 1; tries = 10; }
        else if (lv == 2) { num = r.nextInt(100) + 1; tries = 7; }
        else { num = r.nextInt(500) + 1; tries = 5; }

        time = 30;

        msg.setText("Guess the number!");
        tr.setText("Tries: " + tries);
        tm.setText("Time: " + time);

        in.setText("");
        in.setEnabled(true);
        chk.setEnabled(true);

        startTimer();
    }

    private void startTimer() {
        if (t != null) t.stop();
        t = new Timer(1000, e -> {
            time--;
            tm.setText("Time: " + time);
            if (time <= 0) {
                t.stop();
                fail("⏳ Time’s up! You lost.");
            }
        });
        t.start();
    }

    private void check() {
        String s = in.getText().trim();
        if (s.isEmpty()) return;

        int g;
        try {
            g = Integer.parseInt(s);
        } catch (Exception ex) {
            msg.setText("Enter numbers only!");
            return;
        }

        tries--;
        tr.setText("Tries: " + tries);

        if (g == num) {
            t.stop();
            score++;
            win();
        } else if (g > num) {
            msg.setText("Too high!");
        } else {
            msg.setText("Too low!");
        }

        if (tries == 0) {
            t.stop();
            fail("❌ No tries left! You lost.");
        }
    }

    private void win() {
        JOptionPane.showMessageDialog(this, 
                "✔ Correct! Score: " + score,
                "You Win!", JOptionPane.INFORMATION_MESSAGE);
        reset();
    }

    private void fail(String m) {
        JOptionPane.showMessageDialog(this, 
                m + "\nNumber was: " + num + "\nScore: " + score,
                "Game Over", JOptionPane.ERROR_MESSAGE);
        reset();
    }

    private void reset() {
        msg.setText("Choose difficulty to start");
        tr.setText("");
        tm.setText("");
        in.setEnabled(false);
        chk.setEnabled(false);
        in.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().setVisible(true));
    }
}
