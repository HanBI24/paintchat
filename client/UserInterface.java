package socket;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame {

	// �׸���
	JPanel paintPanel;
	JPanel colorPanel;

	JButton[] colorBtn = new JButton[6];
	String[] colorBtnText = { "red", "orange", "yellow", "green", "blue", "pink" };
	Color[] color = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink };

	PaintCanvas paintCanvas;
	JComboBox jcb;

	// ä��
	JPanel chatPanel;
	JPanel chatInputPanel;
	JTextArea chatArea;
	JTextField chatInputField;
	JButton sendBtn;

	public UserInterface(){
        paintCanvas=new PaintCanvas();
        colorPanel = new JPanel(new FlowLayout());
        for(int i=0; i<6; i++){
            colorBtn[i]=new JButton(colorBtnText[i]);
            colorBtn[i].setBackground(color[i]);
            colorPanel.add(colorBtn[i]);
        }

        String[] str = { "10", "15", "20", "25", "30" };
        jcb = new JComboBox(str);

        colorPanel.add(jcb);

        paintPanel=new JPanel(new BorderLayout());
        paintPanel.add(colorPanel, BorderLayout.NORTH);
        paintPanel.add(paintCanvas,BorderLayout.CENTER);

        ///
        chatPanel=new JPanel(new BorderLayout());
        chatArea = new JTextArea(15,40);
        chatArea.setLineWrap(true); //�ڵ� ����
        chatArea.setWrapStyleWord(true); //���� �ѱ� �� ���� ������ �ܾ ���࿡ ���� ������ �ʵ��� �ϱ�
        chatArea.setEditable(false);
        chatArea.setVisible(true);
        JScrollPane qScroller = new JScrollPane(chatArea);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //���� ��ũ�ѹ� ǥ�� ��å : �׻� �����ֱ�
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);// ���� ��ũ�ѹ� ǥ�� ��å : �׻� �����ֱ�

        chatInputPanel=new JPanel(new FlowLayout());
        chatInputField = new JTextField(20);
        chatInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    sendBtn.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        sendBtn = new JButton("Send");
        sendBtn.addActionListener(new ActionListener() {
            @Override
public void actionPerformed(ActionEvent e) {
                if(chatInputField.getText().length()>0) {
                    chatArea.append(chatInputField.getText() + "\n");
                    chatInputField.setText("");
                }
            }
        });
        chatPanel.add(qScroller,BorderLayout.CENTER);
        chatPanel.add(chatInputPanel,BorderLayout.SOUTH);

        chatInputPanel.add(chatInputField);
        chatInputPanel.add(sendBtn);

        setLayout(new BorderLayout());
        add(paintPanel,BorderLayout.WEST);
        add(chatPanel,BorderLayout.EAST);

        setBounds(100, 100, 1200, 600);
        setResizable(true);
        setVisible(true);

        paintCanvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                paintCanvas.setX(e.getX() - 15);
                paintCanvas.setY(e.getY() - 15);
                paintCanvas.repaint();
            }
        });
    }
}