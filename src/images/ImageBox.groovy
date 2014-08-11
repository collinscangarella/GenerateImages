package images

import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.image.BufferedImage

import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JTextField
import javax.swing.border.Border
import javax.swing.BorderFactory

public class ImageBox extends JFrame implements ActionListener{
	private static final long serialVersionUID = 6380910358227184259L
	ImageGenerator ig = new ImageGenerator()
	JPanel jp = new JPanel()
	JTextArea r = new JTextArea()
	JTextArea g = new JTextArea()
	JTextArea b = new JTextArea()
	GridBagConstraints c = new GridBagConstraints()
	JLabel jl
	public ImageBox(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE)
		this.getContentPane().add(jp)
		JButton jb = new JButton("Calculate")
		jb.addActionListener(this)
		GridBagLayout gbl = new GridBagLayout()
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10)
		r.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder))
		g.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder))
		b.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder))
		r.setTabSize(4)
		g.setTabSize(4)
		b.setTabSize(4)
		r.text = "(x&y)*(x+y)/120";
		g.text = "double r = Math.tan(x/110)*Math.tan(y/110)\nint result = Math.abs((int)(r + 45))\nresult>255?255:result"
		b.text = "int a\nint b\nif (x < y) {\n\ta = x\n\tb = y\n} else {\n\tb = x\n\ta = y\n}\nint k=0\nfor (int i = a; i < b; i++){\n\tk = k+i\n}\nk/6>0?k/6:0"
		jp.setLayout(gbl)
		c.gridx = 0
		c.gridy = 0
		jp.add(new JLabel("Red Value:"), c)
		c.gridx = 0
		c.gridy = 1
		jp.add(new JLabel("Green Value:"), c)
		c.gridx = 0
		c.gridy = 2
		jp.add(new JLabel("Blue Value:"), c)
		c.gridx = 1
		c.gridy = 0
		c.fill = GridBagConstraints.HORIZONTAL;
		jp.add(r, c)
		c.gridx = 1
		c.gridy = 1
		jp.add(g, c)
		c.gridx = 1
		c.gridy = 2
		jp.add(b, c)
		c.gridx = 0
		c.gridy = 3
		c.gridwidth = 2
		jp.add(jb, c)
		setImage()
		c.gridx = 2
		c.gridy = 0
		c.gridwidth = 2
		c.gridheight = 4
		this.jp.add(jl,c)
		this.pack()
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()))
	}
	public void setImage(){
		try{
			this.jl = new JLabel(new ImageIcon(ig.getImage(512, 512, r.getText(), g.getText(), b.getText())))
		} 
		catch (e) { 
			JOptionPane.showMessageDialog(null, "Incorrect Syntax", "", JOptionPane.INFORMATION_MESSAGE);
			this.jl = new JLabel()
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.jp.remove(jl)
		setImage()
		this.jp.add(jl,c)
		this.paintComponents(this.getGraphics())
		this.repaint()
	}
}

