import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KniGuiV2 extends JPanel implements ActionListener{
    KnightsV1 kt;
    JSlider js;
    JButton res;
    List<List<Integer>> pos = new ArrayList<>();

    SwingWorker<Void,Void> linker;
    KniGuiV2(JSlider js,JButton res) throws IOException {
        this.js=js;
        this.res=res;

        this.setBackground(Color.black);

            kt = new KnightsV1();
            linker=new SwingWorker<>(){

                @Override
                public Void doInBackground() throws InterruptedException {
                    for(int i=1;i<65;i++){
                        boolean flag = true;
                        for(int j=0;j<8;j++)
                        {
                            for(int k=0;k<8;k++)
                            {
                                if(kt.a[j][k]==i)
                                {
                                    List<Integer> tpos = new ArrayList<>();
                                    tpos.add(k);
                                    tpos.add(j);
                                    pos.add(tpos);
//                                    tpos.clear();
                                    flag=false;
//                                    System.out.println(pos);
                                    repaint();
                                    try {
                                        Thread.sleep(js.getValue());
                                    }
                                    catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }

                            }
                            if(!flag)
                                break;
                        }
//                        linker.cancel(true);
                    }
                    return null;
                }
            };
            linker.execute();

    }
    public void actionPerformed(ActionEvent e){
        linker.cancel(true);
        System.out.println("hello");
        if(e.getSource()==res)
        {
            pos = new ArrayList<>();
            linker.execute();
            System.out.println("hello");
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        int irow =1,icol=1;

        int cnt=0;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(new Color(157, 255,0));
        for (int i = 0; i < getHeight(); i += getHeight() / 8)
            g1.drawLine(0, i, getWidth(), i);
        for (int i = 0; i < getWidth(); i += getWidth() / 8)
            g1.drawLine(i, 0, i, getHeight());
        g1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        for(int i=0;i<pos.size();i++){
            if(i==0){
                irow=pos.get(i).get(1)+1;
                icol=pos.get(i).get(0)+1;
            }
                cnt++;
            g1.setColor(Color.cyan);
            g1.drawString(String.valueOf(cnt),getWidth()*(pos.get(i).get(0)+1)/8-getWidth()/16,getHeight()*(pos.get(i).get(1)+1)/8-getHeight()/16);
            if(i>0)
            g1.setColor(new Color(255, 52, 191));
            g1.drawLine(getWidth()*icol/8-getWidth()/16,getHeight()*irow/8-getHeight()/16,getWidth()*(pos.get(i).get(0)+1)/8-getWidth()/16,getHeight()*(pos.get(i).get(1)+1)/8-getHeight()/16);
//            System.out.println(pos);
            irow=pos.get(i).get(1)+1;
            icol=pos.get(i).get(0)+1;
        }
//        cnt=0;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame J = new JFrame("Knight's Tour");
            J.getContentPane().setLayout(new BoxLayout(J.getContentPane(),BoxLayout.Y_AXIS));
//                J.getContentPane().setBorder(new EmptyBorder(new Insets(20,20,20,20)));
            J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            J.setSize(600, 600);
            JPanel np = new JPanel();
            np.setLayout(new BoxLayout(np,BoxLayout.X_AXIS));
            JLabel spd = new JLabel("Speed :");
            spd.setForeground(Color.white);
            spd.setFont(new Font("Comic Sans MS",Font.BOLD,15));
            JSlider js = new JSlider(0,300,100);
            js.setBackground(Color.black);
            np.setBackground(Color.black);
            np.add(spd);
            np.add(js);
            np.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
            JButton b = new JButton("Reset");
            try {
                J.add(new KniGuiV2(js,b));
            } catch (IOException e) {
                e.printStackTrace();
            }
            np.add(b);
            J.add(np);

            J.setVisible(true);
        });

    }
}
