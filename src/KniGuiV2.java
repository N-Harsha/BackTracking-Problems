import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KniGuiV2 extends JPanel{
    KnightsV1 kt;

    List<List<Integer>> pos = new ArrayList<List<Integer>>();

    SwingWorker<Void,Void> linker;
    KniGuiV2(){

        this.setBackground(Color.black);
        try {
            kt = new KnightsV1();
            linker=new SwingWorker<>(){

                @Override
                public Void doInBackground() throws InterruptedException {
                    for(int i=2;i<65;i++){
                        boolean flag = true;
                        for(int j=0;j<8;j++)
                        {
                            for(int k=0;k<8;k++)
                            {
                                if(kt.a[j][k]==i)
                                {
                                    List<Integer> tpos = new ArrayList<Integer>();
                                    tpos.add(k);
                                    tpos.add(j);
                                    pos.add(tpos);
//                                    tpos.clear();
                                    flag=false;
//                                    System.out.println(pos);
                                    repaint();
                                    Thread.sleep(100);
                                    break;
                                }

                            }
                            if(!flag)
                                break;
                        }
                    }
                    return null;}
            };
            linker.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        int irow =1,icol=1;
        int cnt=1;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(new Color(157, 255,0));
        for (int i = 0; i < getHeight(); i += getHeight() / 8)
            g1.drawLine(0, i, getWidth(), i);
        for (int i = 0; i < getWidth(); i += getWidth() / 8)
            g1.drawLine(i, 0, i, getHeight());
        g1.setColor(Color.cyan);
        g1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g1.drawString("1",getWidth()/16,getHeight()/16);
        for(int i=0;i<pos.size();i++){
            cnt++;
            g1.drawString(String.valueOf(cnt),getWidth()*(pos.get(i).get(0)+1)/8-getWidth()/16,getHeight()*(pos.get(i).get(1)+1)/8-getHeight()/16);
            g1.drawLine(getWidth()*icol/8-getWidth()/16,getHeight()*irow/8-getHeight()/16,getWidth()*(pos.get(i).get(0)+1)/8-getWidth()/16,getHeight()*(pos.get(i).get(1)+1)/8-getHeight()/16);
            irow=pos.get(i).get(1)+1;
            icol=pos.get(i).get(0)+1;
        }
        cnt=0;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame J = new JFrame("Knight's Tour");
                J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                J.setSize(600, 600);
                J.setContentPane(new KniGuiV2());
                J.setVisible(true);
            }
        });

    }
}