package BomberInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;

 public class BomberMapMake extends JFrame {
	private int x;
	private int y;

	private String[] s;
	private Button_ex[][] button;

	public BomberMapMake(String title, int x, int y) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, x * 50, y * 50 + 30);
		setVisible(true);
		setTitle(title);
		this.x = x;
		this.y = y;

		s = new String[3];
		s[1] = "pics/wall.png";
		s[0] = "pics/blank.png";
		s[2] = "pics/WoodenBox.png";
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("close");
				writeMapToTextFile();
			}

		});

		this.setLayout(null);
		button = new Button_ex[14][14];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < 14; j++) {

				button[i][j] = new Button_ex("", new ImageIcon("pics/blank.png"));
				button[i][j].indexi = i;
				button[i][j].indexj = j;
				button[i][j].setBounds(j * 50, i * 50, 50, 50);
				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 14; i++) {
							for (int j = 0; j < 14; j++) {
								if (e.getSource() == button[i][j]) {
									button[i][j].state++;

									if (button[i][j].state == 3) {
										button[i][j].state = 0;
									}
									button[i][j].setIcon(new ImageIcon(s[button[i][j].state]));

								}
							}
						}

					}
				};
				button[i][j].addActionListener(actionListener);
				this.add(button[i][j]);
			}
		}


	}

	private void writeMapToTextFile() {
		try {
			FileOutputStream fout = new FileOutputStream("BomberMap.txt");
			String textStr = "";

			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 14; j++) {
					if (button[i][j].state == 0) {
						textStr += "0 ";
					} else if (button[i][j].state == 1) {
						textStr += "3 ";
					} else if (button[i][j].state == 2) {
						textStr += "2 ";
					}
				}
				textStr += "\n";
				byte c[] = textStr.getBytes();//converting string into byte array
				fout.write(c);
				textStr = "";
			}
			fout.close();

			System.out.println("success...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	public void paint(Graphics g) {

	}


	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
}
