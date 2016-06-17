package viewcontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import chessPieces.ChessPiece;
import chessPieces.King;
import model.ChessGame;

public class MainGame extends JPanel {
	private Frame frame;
	private Image img;

	private JPanel topAndMenu;
	private JPanel leftBlackHit;
	private JPanel rightWhiteHit;
	private JPanel centerBoard;
	private JPanel bottomBoard;
	private BackButton back;
	private BackButton backMove;
	private SaveButton save;
	private JTextField saveName;

	private ChessGame game;
	private ChessPiece currentFigure;
	private FieldWrapper currentField;
	private String[] felder = { "A", "B", "C", "D", "E", "F", "G", "H" };
	private FieldWrapper[][] field;

	private boolean gameOver;

	private MainGameListeners listener;

	public MainGame(String img, Frame frame) {
		this(new ImageIcon(img).getImage(), frame);
	}

	public MainGame(Image img, Frame frame) {

		setGameOver(false);

		this.frame = frame;
		this.img = img;
		this.listener = new MainGameListeners(this);

		field = new FieldWrapper[8][8];
		game = new ChessGame(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(screenSize);
		setMinimumSize(screenSize);
		setMaximumSize(size);
		setSize(screenSize);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(createBoard(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(createBack(), gbc);
	}

	public ChessGame getGame() {
		return game;
	}

	public void setGame(ChessGame game) {
		this.game = game;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}

	public JPanel createBoard() {
		centerBoard = new JPanel();
		centerBoard.setLayout(new GridLayout(10, 10));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080)
			centerBoard.setPreferredSize(new Dimension(950, 950));

		centerBoard.setOpaque(false);

		JLabel x = new JLabel("");
		centerBoard.add(x);

		for (int i = 7; i >= 0; i--) {
			JLabel l = new JLabel(felder[7 - i], SwingConstants.CENTER);
			l.setBackground(new Color(255, 255, 255));
			l.setForeground(new Color(255, 255, 255));
			if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
				l.setFont(new Font("Century", 0, 60));
			} else {
				l.setFont(new Font("Century", 0, 40));
			}
			centerBoard.add(l);
		}

		x = new JLabel("");
		centerBoard.add(x);

		for (int i = 0; i < 8; i++) {

			JLabel l = new JLabel("" + (8 - i), SwingConstants.CENTER);
			l.setBackground(new Color(255, 255, 255));
			l.setForeground(new Color(255, 255, 255));

			if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
				l.setFont(new Font("Century", 0, 60));
			} else {
				l.setFont(new Font("Century", 0, 40));
			}
			centerBoard.add(l);

			for (int j = 0; j < 8; j++) {

				if (i % 2 == 1) {
					if (j % 2 == 1) {
						if (i == 1) {
							WhiteField b = new WhiteField("./textures/MainGame/Pawn_Black_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							field[i][j] = b;
							centerBoard.add(b);
						} else if (i == 7 && j == 1) {
							WhiteField b = new WhiteField("./textures/MainGame/Knight_White_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 3) {
							WhiteField b = new WhiteField("./textures/MainGame/Queen_White_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 5) {
							WhiteField b = new WhiteField("./textures/MainGame/Bishop_White_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 7) {
							WhiteField b = new WhiteField("./textures/MainGame/Rook_White_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else {
							WhiteField b = new WhiteField();
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						}
					} else {
						if (i == 1) {
							BlackField b = new BlackField("./textures/MainGame/Pawn_Black_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 0) {
							BlackField b = new BlackField("./textures/MainGame/Rook_White_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 2) {
							BlackField b = new BlackField("./textures/MainGame/Bishop_White_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 4) {
							BlackField b = new BlackField("./textures/MainGame/King_White_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 7 && j == 6) {
							BlackField b = new BlackField("./textures/MainGame/Knight_White_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else {
							BlackField b = new BlackField();
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						}

					}
				} else {
					if (j % 2 == 0) {
						if (i == 0 && j == 0) {
							WhiteField b = new WhiteField("./textures/MainGame/Rook_Black_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 2) {
							WhiteField b = new WhiteField("./textures/MainGame/Bishop_Black_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 4) {
							WhiteField b = new WhiteField("./textures/MainGame/King_Black_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 6) {
							WhiteField b = new WhiteField("./textures/MainGame/Knight_Black_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 6) {
							WhiteField b = new WhiteField("./textures/MainGame/Knight_White_WhiteField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else {
							WhiteField b = new WhiteField();
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						}

					} else {
						if (i == 0 && j == 1) {
							BlackField b = new BlackField("./textures/MainGame/Knight_Black_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 3) {
							BlackField b = new BlackField("./textures/MainGame/Queen_Black_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 5) {
							BlackField b = new BlackField("./textures/MainGame/Bishop_Black_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 0 && j == 7) {
							BlackField b = new BlackField("./textures/MainGame/Rook_Black_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else if (i == 6) {
							BlackField b = new BlackField("./textures/MainGame/Pawn_White_BlackField.png");
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						} else {
							BlackField b = new BlackField();
							b.addActionListener(listener);
							b.addMouseListener(listener);
							centerBoard.add(b);
							field[i][j] = b;
						}
					}
				}
			}

			x = new JLabel();
			centerBoard.add(x);
		}

		for (int i = 10; i > 0; i--) {
			x = new JLabel();
			centerBoard.add(x);
		}

		// testField();

		return centerBoard;
	}

	public JPanel createBack() {
		bottomBoard = new JPanel();
		bottomBoard.setLayout(new FlowLayout());
		bottomBoard.setOpaque(false);

		back = new BackButton();
		save = new SaveButton();

		back.addActionListener(listener);
		save.addActionListener(listener);

		back.setVisible(true);
		save.setVisible(true);

		bottomBoard.add(back);
		bottomBoard.add(save);

		return bottomBoard;
	}

	// Debug Method --> delete
	public void testField() {
		FieldWrapper[][] temp = getField();
	}

	public void playSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("sounds/MainMenu_Hover_Click.wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public void gameOver(boolean b){
		setGameOver(true);

		if(b){
			JOptionPane.showMessageDialog(null, "Game Over \nWhite Won");
		}else{
			JOptionPane.showMessageDialog(null, "Game Over \nBlack Won");
		}

		getFrame().toggleMainGame(false);
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public JPanel getTopAndMenu() {
		return topAndMenu;
	}

	public void setTopAndMenu(JPanel topAndMenu) {
		this.topAndMenu = topAndMenu;
	}

	public JPanel getLeftBlackHit() {
		return leftBlackHit;
	}

	public void setLeftBlackHit(JPanel leftBlackHit) {
		this.leftBlackHit = leftBlackHit;
	}

	public JPanel getRightWhiteHit() {
		return rightWhiteHit;
	}

	public void setRightWhiteHit(JPanel rightWhiteHit) {
		this.rightWhiteHit = rightWhiteHit;
	}

	public JPanel getCenterBoard() {
		return centerBoard;
	}

	public void setCenterBoard(JPanel centerBoard) {
		this.centerBoard = centerBoard;
	}

	public JPanel getBottomBoard() {
		return bottomBoard;
	}

	public void setBottomBoard(JPanel bottomBoard) {
		this.bottomBoard = bottomBoard;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = (BackButton) back;
	}

	public String[] getFelder() {
		return felder;
	}

	public void setFelder(String[] felder) {
		this.felder = felder;
	}

	public MainGameListeners getListener() {
		return listener;
	}

	public void setListener(MainGameListeners listener) {
		this.listener = listener;
	}

	public FieldWrapper[][] getField() {
		return field;
	}

	public void setField(FieldWrapper[][] field) {
		this.field = field;
	}

	public void setBack(BackButton back) {
		this.back = back;
	}

	public ChessPiece getCurrentFigure() {
		return currentFigure;
	}

	public void setCurrentFigure(ChessPiece currentFigure) {
		this.currentFigure = currentFigure;
	}

	public FieldWrapper getCurrentField() {
		return currentField;
	}

	public void setCurrentField(FieldWrapper currentField) {
		this.currentField = currentField;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public BackButton getBackMove() {
		return backMove;
	}

	public void setBackMove(BackButton backMove) {
		this.backMove = backMove;
	}

	public SaveButton getSave() {
		return save;
	}

	public void setSave(SaveButton save) {
		this.save = save;
	}

	public JTextField getSaveName() {
		return saveName;
	}

	public void setSaveName(JTextField saveName) {
		this.saveName = saveName;
	}
}
