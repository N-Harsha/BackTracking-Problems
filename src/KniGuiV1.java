import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class KniGuiV1 extends JFrame{
    KnightsV1 kt;
    Timer t;
    KniGuiV1() throws IOException {
        kt = new KnightsV1();
        setSize(600,600);
        setTitle("Knight's Tour");
//        paint(Graphics g);
        setVisible(true);
        MyJPanel j = new MyJPanel();
        j.setSize(getWidth(),getHeight());
        add(j);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class MyJPanel extends JPanel {
        int xnow = 1, ynow = 1;
        Graphics2D g1;
        int i=2;
        public void paint(Graphics g) {
            super.paintComponents(g);

            setBackground(Color.black);
            g1  = (Graphics2D) g;
            g1.setStroke(new BasicStroke(5));
            g1.setColor(Color.PINK);
            for (int i = 0; i < getHeight(); i += getHeight() / 8)
                g1.drawLine(0, i, getWidth(), i);
            for (int i = 0; i < getWidth(); i += getWidth() / 8)
                g1.drawLine(i, 0, i, getHeight());
            g1.setColor(Color.red);
            g1.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            g1.setColor(Color.red);
            g1.setBackground(Color.black);
            g1.drawString("1", getWidth() / 16, getHeight() / 16);
                i=2;
                Draw d = new Draw();
                d.execute();
//                d.cancel(true);

                i++;
            if(i==10)
                i=2;
        }
        class Draw extends SwingWorker<Void,Void>{

            @Override
            protected Void doInBackground(){

                try {
                    linker(g1, i);
//                    i++;
                    Thread.sleep(500);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        void linker(Graphics2D g1, int i) {
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

//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Draw d = new Draw();
//            d.execute();
//        }

//            (new Draw()).execute();
//        }
//
//    }


//            for(int i=2;i<65;i++){
//                for(int j=0;j<8;j++)
//                {
//                    for(int k=0;k<8;k++)
//                    {
//                        if(kt.a[j][k]==i){
//                            g1.drawString(String.valueOf(i),getWidth()*(k+1)/8-getWidth()/16,getHeight()*(j+1)/8-getHeight()/16);
//                            g1.drawLine(getWidth()*ynow/8-getWidth()/16,getHeight()*xnow/8-getHeight()/16,getWidth()*(k+1)/8-getWidth()/16,getHeight()*(j+1)/8-getHeight()/16);
//                            System.out.println(Thread.currentThread().getName());
//                            xnow=j+1;
//                            ynow=k+1;
//                            break;
//                        }
//                    }
//                }
//            }

    }
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            try {
                new KniGuiV1();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }}
//}}
