import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku extends JPanel implements ActionListener {
    JTextField[][] a = new JTextField[9][9];
    JLabel spd = new JLabel("Speed :");
    int speed;
    JTextField sp = new JTextField("    ");
    JSlider js;
    JPanel p = new JPanel();
    JPanel p1 = new JPanel();
    boolean flag = false;
    boolean running = false;
    JPanel[][] pa = new JPanel[3][3];
    int[][] d={{3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    int[][] td={{3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    JButton s = new JButton("Solve");
    JButton r = new JButton("Reset");
    JButton m = new JButton("Modify");
    Color c = new Color(215, 97, 255);

    Sudoku(){
        setBackground(Color.black);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(new Insets(5,5,5,5)));

        p.setBackground(Color.black);
        p1.setBackground(Color.black);
        add(p);
        p.setLayout(new GridLayout(3,3,5,5));
        for(int i =0 ;i<3;i++)
            for(int j=0;j<3;j++)
            {
                pa[i][j]= new JPanel();
                pa[i][j].setLayout(new GridLayout(3,3,3,3));
                pa[i][j].setBackground(Color.black);
                p.add(pa[i][j]); }
        p.setBorder(new EmptyBorder(new Insets(5,5,10,5)));

        r.setBackground(new Color(47, 248, 118));
        r.setForeground(Color.black);
        r.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        r.addActionListener(this);
        r.setSize(30,80);
        r.setFocusable(false);
        r.setHorizontalAlignment(JButton.RIGHT);

        m.setBackground(new Color(182, 255, 64));
        m.setForeground(Color.black);
        m.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        m.addActionListener(this);
        m.setSize(30,80);
        m.setFocusable(false);
        m.setHorizontalAlignment(JButton.RIGHT);

        s.setBackground(new Color(0, 255, 187));
        s.setForeground(Color.black);
        s.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        s.addActionListener(this);
        s.setSize(30,80);
        s.setFocusable(false);
        s.setHorizontalAlignment(JButton.RIGHT);

        p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
        p1.add(s);
        p1.add(Box.createRigidArea(new Dimension(100,0)));
        p1.add(m);
        p1.add(Box.createRigidArea(new Dimension(100,0)));
        p1.add(r);
        p1.setBorder(new EmptyBorder(new Insets(0,0,5,0)));
        func1();

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
        js = new JSlider(0,100,20);
        js.setBackground(Color.black);
        p2.setBackground(Color.black);

        spd.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        spd.setForeground(Color.white);
        p2.add(spd);
        p2.add(js);
        p2.setBorder(new EmptyBorder(new Insets(0,5,10,5)));
        add(p2);
        add(p1);
    }

    public int checker(int num){
        if(num>=6&&num<9)
            return 2;
        else if(num>=3&&num<6)
            return 1;
        else
            return 0;
    }
    void func1(){

        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(d[i][j]!=0)
                    a[i][j]=new JTextField(String.valueOf(d[i][j]));
                else
                    a[i][j]=new JTextField();
                a[i][j].setEditable(flag);
                a[i][j].setBackground(Color.white);
                a[i][j].setOpaque(true);
                a[i][j].setFont(new Font("MV Boli",Font.BOLD,15));
                a[i][j].setHorizontalAlignment(JTextField.CENTER);

                a[i][j].setBorder(BorderFactory.createLineBorder(c,3));
                pa[checker(i)][checker(j)].add(a[i][j]);
            }
        }
    }

    void func2(int row,int col){
        speed = js.getValue();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                if(d[i][j]!=0) {
                    a[i][j].setText(String.valueOf(d[i][j]));
                    a[i][j].setBorder(BorderFactory.createLineBorder( new Color(132, 255, 0),3));
                }
                else {
                    a[i][j].setText("");
                    a[i][j].setBorder(BorderFactory.createLineBorder(c,3));
                }
            }
        if(row!=8&&col!=0)
        {
            try {
                a[row][col - 1].setBorder(BorderFactory.createLineBorder(new Color(253, 14, 14), 3));
            } catch (ArrayIndexOutOfBoundsException e) {
                a[row][8].setBorder(BorderFactory.createLineBorder(new Color(253, 14, 14), 3));
            }
        }
    }

    boolean is_valid(int row,int col,int x){
    for(int i=0;i<9;i++) {
        if (d[row][i] == x)
            return false;
        if (d[i][col] == x)
            return false;
    }
    for(int i=row-row%3;i<row-row%3+3;i++)
        for(int j=col-col%3;j<col-col%3+3;j++)
            if(d[i][j]==x)
                return false;
    return true;

    }

    boolean solver(int row,int col){
    if(row==8&&col==9)
        return true;
    if(col==9){
        row++;
        col=0;
    }
    if(d[row][col]!=0)
        return solver(row,col+1);
    else{
        for(int i=1;i<=9;i++)
        {
            if(is_valid(row,col,i))
            {
                d[row][col]=i;
                func2(row,col);
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(solver(row,col+1))
                    return true;
            }
            d[row][col]=0;
            func2(row,col);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    return false;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==s&&! running){
        running=true;
        SwingWorker<Void,Void> sw = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                solver(0,0);
                running = false;
                return null;
            }
        };
        sw.execute();
    }
     else if(e.getSource()==r&&!running){
        running= true;
        SwingWorker<Void,Void> sw1 = new SwingWorker<>() {
            @Override
            protected Void doInBackground(){
                for(int i =8;i>=0;i--)
                    for(int j=8;j>=0;j--) {
                        d[i][j] = td[i][j];
                        if(d[i][j]!=0)
                            a[i][j].setText(String.valueOf(d[i][j]));
                        else
                            a[i][j].setText("");
                        a[i][j].setBorder(BorderFactory.createLineBorder(c,3));
                        try{
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                running = false;
                return null;
            }
        };
        sw1.execute();

    }
     else
        JOptionPane.showMessageDialog(this,"please wait");



    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame jf = new JFrame("Sudoku solver");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,500);
        jf.setContentPane(new Sudoku());
        jf.setVisible(true);
    });


    }


}
