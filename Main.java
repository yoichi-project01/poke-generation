/*/**
 * レビュアー
 * D.K
 * S.K
 * S.K
 * T.T
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends Object{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // フレームの設定
                Poke_generation frame = new Poke_generation();
                frame.setTitle("Pokemon Timelag");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 800);
                frame.setVisible(true);
            }
        });
    }
}
