import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingDemo extends JFrame {
  public static void main(String args[]) {
    new SwingDemo();
  }
  
  SwingDemo() {
    JLabel jlbHelloWorld = new JLabel("Hello World"); 
    add(jlbHelloWorld);
    this.setSize(100, 100);
    setVisible(true);
  }
}

