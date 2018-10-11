package technology.rocketjump.rebbl.bingo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static technology.rocketjump.rebbl.bingo.BingoItem.BingoEventLikelihood.*;

public class App {

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("This must be called with a single string argument - the coaches name");
			return;
		}

		String coachName = args[0];
		String csvContent = new BingoCardGenerator().generateBingoCard(coachName);

		System.out.println("\n");
		System.out.println(csvContent);

		File outputFile = new File(coachName + ".csv");
		FileUtils.write(outputFile, csvContent, "UTF-8");
	}
}
