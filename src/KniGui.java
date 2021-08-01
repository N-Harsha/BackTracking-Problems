import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class KniGui extends JFrame{
    KnightsV1 kt;
    KniGui() throws IOException {
        kt = new KnightsV1();
        setSize(800,800);
        setTitle("Knight's Tour");

        setVisible(true);
        MyJPanel j = new MyJPanel();
        j.setSize(getWidth(),getHeight());
        add(j);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class MyJPanel extends JPanel{
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setBackground(Color.black);
            Graphics2D g1= (Graphics2D) g;
            g1.setStroke(new BasicStroke(5));
            g1.setColor(Color.PINK);
            for(int i=0;i<getHeight();i+=getHeight()/8)
                g1.drawLine(0,i,getWidth(),i);
            for(int i=0;i<getWidth();i+=getWidth()/8)
                g1.drawLine(i,0,i,getHeight());
            g1.setColor(Color.cyan);

                int xnow=1,ynow=1;

                g1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g1.setColor(Color.cyan);
                g1.drawString("1",getWidth()/16,getHeight()/16);
                for(int i=2;i<65;i++){
                    boolean flag = true;
                    for(int j=0;j<8;j++)
                    {
                        for(int k=0;k<8;k++)
                        {
                            if(kt.a[j][k]==i){
                                g1.drawString(String.valueOf(i),getWidth()*(k+1)/8-getWidth()/16,getHeight()*(j+1)/8-getHeight()/16);
                                g1.drawLine(getWidth()*ynow/8-getWidth()/16,getHeight()*xnow/8-getHeight()/16,getWidth()*(k+1)/8-getWidth()/16,getHeight()*(j+1)/8-getHeight()/16);

                                xnow=j+1;
                                ynow=k+1;
                                flag = false;
                                break;
                            }

                        }
                        if(!flag)
                            break;
                    }
                }
            }

        }

    public static void main(String[] args) throws IOException {
        new KniGui();
    }
}
