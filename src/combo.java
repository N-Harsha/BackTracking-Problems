import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class combo extends JPanel implements ActionListener {
    int[][] a;
    int n=8;
    JSlider js;
    JButton res;
    int speed = 100;
    List<Integer> tpos;
    SwingWorker<Void,Void> Linker;
    List<List<Integer>> pos = new ArrayList<>();
    int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };
//    int cnt=0;
    combo(int i,int j,JSlider js,JButton b){
        this.res = b;
        this.js = js;
        a = new int[8][8];
        a[i][j]=1;
        tpos =new ArrayList<>();
        tpos.add(j);
        tpos.add(i);
        pos.add(tpos);

        //now for some gui

        this.setBackground(Color.black);
        Linker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                solver(i,j,2);
                Thread.sleep(5000);
                pos=new ArrayList<>();
                return null;
            }
        };
        Linker.execute();
    }

    public void actionPerformed(ActionEvent e){
//        if(e.getSource()==res)
//        {
////            pos = new ArrayList<>();
//            System.out.println("hello");
////            repaint();
////            Linker.execute();
//        }
//        System.out.println(e.getSource());
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
                try {
                    g1.drawString(String.valueOf(cnt), getWidth() * (pos.get(i).get(0) + 1) / 8 - getWidth() / 16, getHeight() * (pos.get(i).get(1) + 1) / 8 - getHeight() / 16);
                }
                catch (Exception e)
                {}
            if(i>0) {
                g1.setColor(new Color(255, 52, 191));
                try {
                    g1.drawLine(getWidth() * icol / 8 - getWidth() / 16, getHeight() * irow / 8 - getHeight() / 16, getWidth() * (pos.get(i).get(0) + 1) / 8 - getWidth() / 16, getHeight() * (pos.get(i).get(1) + 1) / 8 - getHeight() / 16);

                g1.drawLine(getWidth() * icol / 8 - getWidth() / 16, getHeight() * irow / 8 - getHeight() / 16, getWidth() * (pos.get(i).get(0) + 1) / 8 - getWidth() / 16, getHeight() * (pos.get(i).get(1) + 1) / 8 - getHeight() / 16);
                irow = pos.get(i).get(1) + 1;
                icol = pos.get(i).get(0) + 1;
                } catch (Exception E) {

                }
            }
        }
        cnt=0;
    }

    boolean is_valid(int row,int col)
    {
        if(row>=0&&row<n&&col>=0&&col<n&&a[row][col]==0)
            return true;
        return false;
    }
    boolean solver(int x,int y,int cnt){
        int next_x,next_y;
        if(cnt==65)
            return true;
        for(int i=0;i<8;i++)
        {
            next_x=x+xMove[i];
            next_y=y+yMove[i];
            if(is_valid(next_x,next_y))
            {
                a[next_x][next_y]=cnt;
                tpos =new ArrayList<Integer>();
                tpos.add(next_y);
                tpos.add(next_x);
                pos.add(tpos);
                try {
                    Thread.sleep(js.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
//                System.out.println(pos);
                if(solver(next_x,next_y,cnt+1))
                    return true;
                else {
                    a[next_x][next_y] = 0;
                    pos.remove(cnt-1);
                    try {
                        Thread.sleep(js.getValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();

                }}
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                JSlider js = new JSlider(0,200,30);
                js.setBackground(Color.black);
                np.setBackground(Color.black);
                np.add(spd);
                np.add(js);
                np.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
                JButton b = new JButton("Reset");
                J.add(new combo(0,0,js,b));
                np.add(b);
                J.add(np);

                J.setVisible(true);
            }
        });
//        new combo(0,0);
    }



}
