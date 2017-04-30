package part1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
//		File trainingSet = new File(args[0]);
		File trainingSet = new File("data/iris-training.txt");
//		File testSet = new File (args[1]);
		File testSet = new File("data/iris-test.txt");
		File outputFile = new File("Output.txt");
		ArrayList<Flower> trainingFlowers = loadDataSets(trainingSet, outputFile, true);
		ArrayList<Flower> testFlowers = loadDataSets(testSet, outputFile, false);

		new Algorithm(trainingFlowers, testFlowers, outputFile);
		
		System.out.println("Program completed.");
		System.exit(0);
	}

	public static ArrayList<Flower> loadDataSets(File readFile, File writeFile, boolean isTraining) {
		try {
			ArrayList<Flower> flowersList = new ArrayList<Flower>();
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

			if(isTraining == false){
				writer.write("Program run on +" + new Date() + "\n");
				writer.write("Actual test set values:" + "\n");
			}

			String currentLine;
			int id = 1;
			while((currentLine = reader.readLine()) != null){
				if(currentLine.length() > 0) {
					String values[] = currentLine.split("\\s+");
					double sepalLength = Double.parseDouble(values[0]);
					double sepalWidth = Double.parseDouble(values[1]);
					double petalLength = Double.parseDouble(values[2]);
					double petalWidth = Double.parseDouble(values[3]);
					String classifier = values[4];
					Flower flower = new Flower(id, sepalLength, sepalWidth, petalLength, petalWidth);

					if(isTraining == true){
						flower.setClassifier(classifier);
					}
					flowersList.add(flower);
					if(isTraining == false){
						writer.write(id + "\t" + sepalLength + "\t" + sepalWidth + "\t" +
						petalLength +"\t" + petalWidth + "\t" + classifier + "\n");
					}
					id ++;
				}
			}
			reader.close();
			writer.close();
			return flowersList;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
