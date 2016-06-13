package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Noah on 02.06.2016.
 */
public class SaveLoad {

	public SaveLoad() {
	}

	public void save(String savename, ChessGame game_info) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(savename + ".txt", true));
		//writer.write(game_info.saveData());
		writer.close();
	}

	public void load(String id) {

	}
}
