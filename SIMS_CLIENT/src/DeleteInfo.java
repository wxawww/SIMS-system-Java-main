import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class DeleteInfo extends JFrame
{
    static ArrayList<Student> stuArr=new ArrayList<>();
    public DeleteInfo(int stunum)
    {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(520,385);
        setTitle("wlfSIMS - 删除学生成绩");
        setResizable(true);
        setLocation((width - this.getWidth()) / 2, (height - this.getHeight()) / 2);
        setIconImage(new ImageIcon(Objects.requireNonNull(Login.class.getResource("img/Logo.png"))).getImage());
        Container fcon = this.getContentPane();
        fcon.setLayout(new GridLayout(8,1));
        JPanel temppan01=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(temppan01);temppan01.setBackground(Color.BLACK);
        JPanel cpan01=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan01);
        JPanel cpan02=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan02);
        JPanel cpan03=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan03);
        JPanel cpan04=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan04);
        JPanel cpan05=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan05);
        JPanel cpan06=new JPanel(new FlowLayout(FlowLayout.CENTER));fcon.add(cpan06);
        JPanel temppan02=new JPanel(new FlowLayout(FlowLayout.RIGHT));fcon.add(temppan02);temppan02.setBackground(Color.BLACK);
        cpan01.setBackground(Color.BLACK);
        cpan02.setBackground(Color.BLACK);
        cpan03.setBackground(Color.BLACK);
        cpan04.setBackground(Color.BLACK);
        cpan05.setBackground(Color.BLACK);
        cpan06.setBackground(Color.BLACK);
        JLabel clab01=new JLabel("学生学号:");clab01.setBackground(Color.BLACK);clab01.setForeground(Color.WHITE);
        JLabel clab02=new JLabel("学生姓名:");clab02.setBackground(Color.BLACK);clab02.setForeground(Color.WHITE);
        JLabel clab03=new JLabel("学生性别:");clab03.setBackground(Color.BLACK);clab03.setForeground(Color.WHITE);
        JLabel clab04=new JLabel("数学:");clab04.setBackground(Color.BLACK);clab04.setForeground(Color.WHITE);
        JLabel clab05=new JLabel("Java:");clab05.setBackground(Color.BLACK);clab05.setForeground(Color.WHITE);
        JLabel clab06=new JLabel("体育:");clab06.setBackground(Color.BLACK);clab06.setForeground(Color.WHITE);
        JTextField ctext01=new JTextField(16);ctext01.setBackground(new Color(40,40,40));ctext01.setForeground(Color.WHITE);
        JTextField ctext02=new JTextField(16);ctext02.setBackground(new Color(40,40,40));ctext02.setForeground(Color.WHITE);
        JTextField ctext03=new JTextField(16);ctext03.setBackground(new Color(40,40,40));ctext03.setForeground(Color.WHITE);
        JTextField ctext04=new JTextField(16);ctext04.setBackground(new Color(40,40,40));ctext04.setForeground(Color.WHITE);
        JTextField ctext05=new JTextField(16);ctext05.setBackground(new Color(40,40,40));ctext05.setForeground(Color.WHITE);
        JTextField ctext06=new JTextField(16);ctext06.setBackground(new Color(40,40,40));ctext06.setForeground(Color.WHITE);
        ctext01.setHorizontalAlignment(JTextField.CENTER);
        ctext02.setHorizontalAlignment(JTextField.CENTER);
        ctext03.setHorizontalAlignment(JTextField.CENTER);
        ctext04.setHorizontalAlignment(JTextField.CENTER);
        ctext05.setHorizontalAlignment(JTextField.CENTER);
        ctext06.setHorizontalAlignment(JTextField.CENTER);
        cpan01.add(clab01);cpan01.add(ctext01);
        cpan02.add(clab02);cpan02.add(ctext02);
        cpan03.add(clab03);cpan03.add(ctext03);
        cpan04.add(clab04);cpan04.add(ctext04);
        cpan05.add(clab05);cpan05.add(ctext05);
        cpan06.add(clab06);cpan06.add(ctext06);


        JButton cbtn01=new JButton("确定删除");
        JButton cbtn02=new JButton("取消删除");
        JLabel clab07=new JLabel("请核对：删除学生的以下成绩");
        clab07.setBackground(Color.BLACK);
        clab07.setForeground(Color.WHITE);
        cbtn01.setBackground(Color.RED);
        cbtn02.setBackground(Color.BLACK);
        cbtn01.setForeground(Color.YELLOW);
        cbtn02.setForeground(Color.WHITE);
        temppan02.add(cbtn01);temppan02.add(cbtn02);
        temppan01.add(clab07);

        clab07.setFont(new Font("仿宋",Font.BOLD,21));
        clab01.setFont(Login.stdfont);
        cbtn02.setFont(Login.stdfont);
        clab03.setFont(Login.stdfont);
        clab04.setFont(Login.stdfont);
        clab05.setFont(Login.stdfont);
        clab06.setFont(Login.stdfont);
        ctext01.setFont(Login.stdfont);ctext01.setEditable(false);
        ctext02.setFont(Login.stdfont);ctext02.setEditable(false);
        ctext03.setFont(Login.stdfont);ctext03.setEditable(false);
        ctext04.setFont(Login.stdfont);ctext04.setEditable(false);
        ctext05.setFont(Login.stdfont);ctext05.setEditable(false);
        ctext06.setFont(Login.stdfont);ctext06.setEditable(false);
        cbtn01.setFont(Login.stdfont);
        cbtn02.setFont(Login.stdfont);



        Statement datasta02;
        ResultSet rs02=null;

        try
        {
            datasta02=Login.databasecon.createStatement();
            rs02=datasta02.executeQuery("SELECT * FROM " + Login.BOOKNAME);
        }
        catch (Exception e04) { System.out.println(e04.getMessage());}


        stuArr.clear();boolean flag01=false;
        try
        {
            while(rs02.next())
            {
                stuArr.add(new Student(rs02.getInt(1),rs02.getString(2),rs02.getString(3), rs02.getString(4),rs02.getString(5),rs02.getString(6)));
            }
        }
        catch (Exception e05)
        {

        }
        for (Student temp : stuArr) {
            if (stunum == temp.StudentID) {
                flag01 = true;
                ctext01.setText(String.valueOf(temp.StudentID));
                ctext02.setText(temp.Name);
                ctext03.setText(temp.Sex);
                ctext04.setText(temp.math);
                ctext05.setText(temp.Java);
                ctext06.setText(temp.sport);
            }
        }
        if(!flag01)
        {
            JOptionPane.showMessageDialog(null,"错误:您的学号不存在","wlf-SIMS 系统警告",JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        setVisible(true);
        validate();

        ActionListener a002=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        };cbtn02.addActionListener(a002);

        ActionListener a001=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int dialogButton;
                dialogButton=JOptionPane.showConfirmDialog(null,"请再次核对是否删除该学生的成绩，删除后不可恢复！","Duyu-SIMS 系统警告",JOptionPane.YES_NO_OPTION);
                if(dialogButton==JOptionPane.YES_OPTION)
                {
                    String SQLStr02="DELETE FROM "+Login.BOOKNAME+" WHERE StudentID="+ctext01.getText();
                    try
                    {
                        PreparedStatement preparedStatement02=Login.databasecon.prepareStatement(SQLStr02);
                        preparedStatement02.execute();
                        JOptionPane.showMessageDialog(null,"wlf-SIMS系统提示：学生删除成功！","wlf-SIMS 系统",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,"wlf-SIMS系统提示：学生删除失败！","wlf-SIMS 系统警告",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };cbtn01.addActionListener(a001);
    }
}


/*
 *End of source code of DeleteInfo.java
 * 2023/06/11 00:37
 * 2023/06/13 09:38
 */