package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import chessPieces.ChessPiece;
import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import viewcontrol.BlackField;
import viewcontrol.FieldWrapper;
import viewcontrol.Frame;
import viewcontrol.WhiteField;

public class BridgeFromFrontEndToBackEnd {

	Frame frame;

	public BridgeFromFrontEndToBackEnd(Frame frame) {
		this.frame = frame;
	}

	public Position determinField(Object o) {
		FieldWrapper[][] temp = frame.getPanel4().getField();

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j].equals(o)) {

					Position p = new Position();
					p.setxValue(i);
					p.setyValue(j);
					return p;
				}
			}
		}
		return null;
	}

	public ChessPiece getFigure(Position p) {
		ChessPiece[][] cp = frame.getPanel4().getGame().getBoard().getField();

		return cp[p.getxValue()][p.getyValue()];
	}

	public String toChessNotation(Position p) {
		String chessNotation = "";

		if (p.getxValue() == 0) {
			chessNotation += "a";
		} else if (p.getxValue() == 1) {
			chessNotation += "b";
		} else if (p.getxValue() == 2) {
			chessNotation += "c";
		} else if (p.getxValue() == 3) {
			chessNotation += "d";
		} else if (p.getxValue() == 4) {
			chessNotation += "e";
		} else if (p.getxValue() == 5) {
			chessNotation += "f";
		} else if (p.getxValue() == 6) {
			chessNotation += "g";
		} else if (p.getxValue() == 7) {
			chessNotation += "h";
		}

		int temp = 8 - p.getyValue();

		chessNotation += temp;
		return chessNotation;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void playUnsucssefullSound() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./sounds/fail_sound.au"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public void validField(Position p) {

		FieldWrapper[][] temp = frame.getPanel4().getField();

		ImageIcon img = (ImageIcon) frame.getPanel4().getCurrentField().getIcon();
		String path = img.getDescription();

		ImageIcon check = (ImageIcon) temp[p.getxValue()][p.getyValue()].getIcon();

		if ((img.getDescription().contains("BlackField") && check.getDescription().contains("WhiteField")
				|| img.getDescription().contains("WhiteField") && check.getDescription().contains("BlackField")))
			path = invertField(img.getDescription());

		ImageIcon newImg = new ImageIcon(path);
		temp[p.getxValue()][p.getyValue()].setIcon(newImg);

		String oldField = replaceOldField(img.getDescription());
		ImageIcon oldImg = new ImageIcon(oldField);

		Position p2 = determinField(frame.getPanel4().getCurrentField());
		temp[p2.getxValue()][p2.getyValue()].setIcon(oldImg);

		frame.getPanel4().repaint();


		String nextPos = toChessNotation(p);

		try {
			frame.getPanel4().getGame().getBoard().move( nextPos,frame.getPanel4().getCurrentFigure());
			frame.getPanel4().getCurrentFigure().setActPos(toChessNotation(p));
		} catch (FieldException e) {
			System.out.println("Move didn't work");
		} catch (PositionException e) {
			e.printStackTrace();
		}
	}

	private String replaceOldField(String path) {
		String[] parts = path.split("[/]");
		String[] fileName = parts[parts.length - 1].split("[.]");

		String newFileName = "";

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (fileName[0].contains("BlackField")) {
			fileName[0] = "EmptyBlackField";

			if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080) {
				fileName[0] += "_fullHD.";
			} else {
				fileName[0] += ".";
			}

		} else {
			fileName[0] = "EmptyWhiteField";

			if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080) {
				fileName[0] += "_fullHD.";
			} else {
				fileName[0] += ".";
			}
		}

		for (int i = 0; i < parts.length - 1; i++) {
			newFileName += parts[i] + "/";
		}

		for (int i = 0; i < fileName.length; i++) {
			newFileName += fileName[i];
		}
		return newFileName;

	}

	public String invertField(String path) {

		String[] parts = path.split("[/]");
		String[] fileName = parts[parts.length - 1].split("[.]");

		String newFileName = "";

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (fileName[0].contains("BlackField")) {
			fileName[0] = fileName[0].replace("BlackField", "WhiteField");

			if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080) {
				fileName[0] += "_fullHD.";
			} else {
				fileName[0] += ".";
			}

		} else {
			fileName[0] = fileName[0].replace("WhiteField", "BlackField");

			if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080) {
				fileName[0] += "_fullHD.";
			} else {
				fileName[0] += ".";
			}
		}

		for (int i = 0; i < parts.length - 1; i++) {
			newFileName += parts[i] + "/";
		}

		for (int i = 0; i < fileName.length; i++) {
			newFileName += fileName[i];
		}
		return newFileName;
	}

	public void allpossibleMoves() {
		ChessPiece cp = frame.getPanel4().getCurrentFigure();
		FieldWrapper[][] temp = frame.getPanel4().getField();

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {

				Position p = new Position();
				p.setxValue(i);
				p.setyValue(j);

				String endPos = toChessNotation(p);

				boolean isPossible = false;
				try {
					//isPossible = cp.move(endPos, frame.getPanel4().getGame().getBoard(), cp);
					isPossible = frame.getPanel4().getGame().getBoard().validMove(endPos, cp);
				} catch (/*ChessPieceException |*/ FieldException | PositionException e1) {
					e1.printStackTrace();
				}

				if (isPossible) {
					frame.getPanel4().playSound();
				} else {
					temp[i][j].setIcon(new ImageIcon("./textures/MainGame/EmptyWhiteField_fullHD.png"));
					temp[i][j].setContentAreaFilled(true);
					temp[i][j].setBackground(new Color(255, 0, 0));
				}
			}
		}
	}

	public void updateField(){
		ChessPiece [][] logicField = frame.getPanel4().getGame().getBoard().getField();
		FieldWrapper [][] guiField = frame.getPanel4().getField();

		for(int i = 0; i < logicField.length; i++){
			for(int j = 0; j < logicField[i].length; j++){
                //Reference for field color
                ImageIcon img = (ImageIcon) guiField[i][j].getIcon();
                String path = img.getDescription();
                //if true -> black

                if(logicField[i][j] == null){
                    if(path.contains("BlackField")){
                        guiField[i][j].setIcon(new BlackField().getIcon());
                    }else{
                        guiField[i][j].setIcon(new WhiteField().getIcon());
                    }

                    continue;
                }

				if (logicField[i][j].getColour()){
					//black
                    switch (logicField[i][j].getName()){
						case "Pawn":
							if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Pawn_Black_BlackField.png").getIcon());
							}else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Pawn_Black_WhiteField.png").getIcon());
                            }
							break;
						case "Knight":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Knight_Black_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Knight_Black_WhiteField.png").getIcon());
                            }
							break;
						case "Bishop":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Bishop_Black_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Bishop_Black_WhiteField.png").getIcon());
                            }
							break;
                        case "Rook":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Rook_Black_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Rook_Black_WhiteField.png").getIcon());
                            }
							break;
						case "King":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/King_Black_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/King_Black_WhiteField.png").getIcon());
                            }
							break;
						case "Queen":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Queen_Black_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Queen_Black_WhiteField.png").getIcon());
                            }
							break;
					}
				}else{
                    //white
                    switch (logicField[i][j].getName()){
                        case "Pawn":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Pawn_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Pawn_White_WhiteField.png").getIcon());
                            }
                            break;
                        case "Knight":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Knight_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Knight_White_WhiteField.png").getIcon());
                            }
                            break;
                        case "Bishop":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Bishop_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Bishop_White_WhiteField.png").getIcon());
                            }
                            break;
                        case "Rook":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Rook_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Rook_White_WhiteField.png").getIcon());
                            }
                            break;
                        case "King":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/King_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/King_White_WhiteField.png").getIcon());
                            }
                            break;
                        case "Queen":
                            if(path.contains("BlackField")){
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Queen_White_BlackField.png").getIcon());
                            }else{
                                guiField[i][j].setIcon(new BlackField("./textures/MainGame/Queen_White_WhiteField.png").getIcon());
                            }
                            break;
                    }
				}
			}
		}

        frame.getPanel4().repaint();
	}


}
