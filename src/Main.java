import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.MinguoChronology;
import java.util.Date;

public class Main
{
    public static LocalDate date = LocalDate.now();
    public static JLabel labbot01=new JLabel("");
    public static JLabel labbot02=new JLabel("");
    public static SimpleDateFormat sdf01=new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf02=new SimpleDateFormat("HH:mm:ss");
    public static JFrame form1;
    public static void main(String[] args)
    {
        form1 = new JFrame("Duyu09开发的小日历v1.0");
        form1.setVisible(true);
        form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form1.setLocationRelativeTo(null);
        form1.setSize(450, 400);
        form1.setMinimumSize(new Dimension(450,400));
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        form1.setLocation((width - form1.getWidth()) / 2, (height - form1.getHeight()) / 2);
        Container con=form1.getContentPane();
        con.setLayout(new BorderLayout());
        JPanel con2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton b1=new JButton("上个月");
        JButton b2=new JButton("下个月");
        JButton b3=new JButton("确定");
        JLabel labyear =new JLabel("当前年份:",JLabel.CENTER);
        JLabel labmonth = new JLabel("当前月份:",JLabel.CENTER);
        JTextField text1=new JTextField(4);
        JTextField text2=new JTextField(4);
        con2.add(labyear);con2.add(text1);con2.add(labmonth);con2.add(text2);
        con2.add(b3);
        con2.add(b1);
        con2.add(b2);
        con.add(con2,BorderLayout.NORTH);
        JPanel pan1=new JPanel();
        pan1.setLayout(new GridLayout(7,8));
        JLabel labs[]=new JLabel[49];
        char chs[]="一二三四五六日".toCharArray();
        pan1.setBackground(new Color(180,180,180));
        for(int p=0;p<=6;++p)
        {
            labs[p]=new JLabel(String.valueOf(chs[p]),JLabel.CENTER);
            labs[p].setFont(new Font("微软雅黑",1,17));
            labs[p].setBackground(new Color(150,150,150));
            pan1.add(labs[p]);
        }
        for(int w=7;w<=48;++w)
        {
            labs[w]=new JLabel("",JLabel.CENTER);
            labs[w].setFont(new Font("微软雅黑",1,12));
            pan1.add(labs[w]);
        }
        con.add(pan1,BorderLayout.CENTER);
        int yearnow = date.getYear();
        int monthnow =date.getMonth().getValue();
        int daynow=date.getDayOfMonth();
        int weeknow=date.getDayOfWeek().getValue();
        text1.setText(String.valueOf(yearnow));
        text2.setText(String.valueOf(monthnow));
        int firstday=LocalDate.of(date.getYear(),date.getMonth(),1).getDayOfWeek().getValue();
        int monlen = date.lengthOfMonth();
        boolean flag01=true;
        if(text1.getText().equals(""+LocalDate.now().getYear()) && text2.getText().equals(""+LocalDate.now().getMonthValue()))
        {
            flag01=false;
        }
        for(int q=firstday;q<=firstday+monlen-1;++q)
        {
            labs[6+q].setText(String.valueOf(q-firstday+1));
            if(flag01==false && LocalDate.now().getDayOfMonth()==q-firstday+1)
            {
                labs[6+q].setForeground(Color.RED);
                labs[6+q].setFont(new Font("黑体",1,16));
            }
            else
            {
                labs[6+q].setForeground(Color.BLACK);
                labs[6+q].setFont(labs[48].getFont());
            }
        }
        form1.validate();

        ActionListener li01=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    if(Integer.parseInt(text1.getText())>2999 || Integer.parseInt(text1.getText())<1000)
                    {throw new YearException();}
                    if(Integer.parseInt(text2.getText())>12 || Integer.parseInt(text2.getText())<1)
                    {throw new MonthException();}
                }
                catch (YearException e01)
                {
                    System.out.println("年异常");
                    if(Integer.parseInt(text1.getText())>2999)
                    {text1.setText("2999");}
                    else
                    {text1.setText("1000");}
                }
                catch (MonthException e02)
                {
                    System.out.println("月异常");
                    if(Integer.parseInt(text2.getText())>12)
                    {text2.setText("12");}
                    else
                    {text2.setText("1");}
                }
                date=LocalDate.of(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),date.getDayOfMonth());
                int firstday=LocalDate.of(date.getYear(),date.getMonth(),1).getDayOfWeek().getValue();
                int monlen = date.lengthOfMonth();
                for(int sd=7;sd<48;++sd)
                {
                    labs[sd].setText("");
                }
                boolean flag01=true;
                if(text1.getText().equals(""+LocalDate.now().getYear()) && text2.getText().equals(""+LocalDate.now().getMonthValue()))
                {
                    flag01=false;
                }
                for(int q=firstday;q<=firstday+monlen-1;++q)
                {
                    labs[6+q].setText(String.valueOf(q-firstday+1));
                    if(flag01==false && LocalDate.now().getDayOfMonth()==q-firstday+1)
                    {
                        labs[6+q].setForeground(Color.RED);
                        labs[6+q].setFont(new Font("黑体",1,16));
                    }
                    else
                    {
                        labs[6+q].setForeground(Color.BLACK);
                        labs[6+q].setFont(labs[48].getFont());
                    }
                }
                form1.validate();
            }
        };
        ActionListener li02=new ActionListener()//上个月按钮
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int month=Integer.parseInt(text2.getText())-1;
                int year=Integer.parseInt(text1.getText());
                if(month<=0)
                {
                    month=12;--year;
                }
                text1.setText(""+year);
                text2.setText(""+month);
                date=LocalDate.of(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),date.getDayOfMonth());
                int firstday=LocalDate.of(date.getYear(),date.getMonth(),1).getDayOfWeek().getValue();
                int monlen = date.lengthOfMonth();
                for(int sd=7;sd<48;++sd)
                {
                    labs[sd].setText("");
                }
                boolean flag01=true;
                if(text1.getText().equals(""+LocalDate.now().getYear()) && text2.getText().equals(""+LocalDate.now().getMonthValue()))
                {
                    flag01=false;
                }
                for(int q=firstday;q<=firstday+monlen-1;++q)
                {
                    labs[6+q].setText(String.valueOf(q-firstday+1));
                    if(flag01==false && LocalDate.now().getDayOfMonth()==q-firstday+1)
                    {
                        labs[6+q].setForeground(Color.RED);
                        labs[6+q].setFont(new Font("黑体",1,16));
                    }
                    else
                    {
                        labs[6+q].setForeground(Color.BLACK);
                        labs[6+q].setFont(labs[48].getFont());
                    }
                }
                form1.validate();
            }
        };
        ActionListener li03=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int month=Integer.parseInt(text2.getText())+1;
                int year=Integer.parseInt(text1.getText());
                if(month>=13)
                {
                    month=1;++year;
                }
                text1.setText(""+year);
                text2.setText(""+month);
                date=LocalDate.of(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),date.getDayOfMonth());
                int firstday=LocalDate.of(date.getYear(),date.getMonth(),1).getDayOfWeek().getValue();
                int monlen = date.lengthOfMonth();
                for(int sd=7;sd<48;++sd)
                {
                    labs[sd].setText("");
                }
                boolean flag01=true;
                if(text1.getText().equals(""+LocalDate.now().getYear()) && text2.getText().equals(""+LocalDate.now().getMonthValue()))
                {
                    flag01=false;
                }
                for(int q=firstday;q<=firstday+monlen-1;++q)
                {
                    labs[6+q].setText(String.valueOf(q-firstday+1));
                    if(flag01==false && LocalDate.now().getDayOfMonth()==q-firstday+1)
                    {
                        labs[6+q].setForeground(Color.RED);
                        labs[6+q].setFont(new Font("黑体",1,16));
                    }
                    else
                    {
                        labs[6+q].setForeground(Color.BLACK);
                        labs[6+q].setFont(labs[48].getFont());
                    }
                }
                form1.validate();
            }
        };
        b3.addActionListener(li01);
        b1.addActionListener(li02);
        b2.addActionListener(li03);
        Font x=new Font("微软雅黑",0,12);
        b1.setFont(x);b2.setFont(x);b3.setFont(x);
        text1.setFont(x);text2.setFont(x);
        labmonth.setFont(x);labyear.setFont(x);
        form1.validate();

        JPanel pan02=new JPanel();
        pan02.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan02.add(labbot01);
        pan02.add(labbot02);
        con.add(pan02,BorderLayout.SOUTH);
        labbot01.setFont(new Font("微软雅黑",1,15));
        labbot02.setFont(new Font("微软雅黑",1,15));
        TimeMutiTh th=new TimeMutiTh();
        th.run();
    }
}
class YearException extends Exception
{

}
class MonthException extends Exception
{

}
class TimeMutiTh implements Runnable
{
    @Override
    public void run()
    {
        while (true)
        {
            Main.labbot01.setText("当前时间:"+Main.sdf01.format(new Date(System.currentTimeMillis())));
            Main.labbot02.setText(Main.sdf02.format(new Date(System.currentTimeMillis())));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.form1.validate();
        }
    }
}