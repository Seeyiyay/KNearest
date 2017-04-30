package part1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {

	public static int k = 1;
	public ArrayList<Flower> trainingSet;
	public ArrayList<Flower> testSet;
	public File outputFile;

	public Algorithm(ArrayList<Flower> trainingSet, ArrayList<Flower> testSet, File outputFile) {
		this.trainingSet = trainingSet;
		this.testSet = testSet;
		this.outputFile = outputFile;
		executeAlgorithm();
	}

	public void executeAlgorithm() {
		for(Flower f : testSet) {
			f.setClassifier(nearestClassifier(f, k));
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
			writer.write("Results obtained from algorithm where K = " + k + ":" + "\n");
			for(Flower f : testSet) {
				writer.write(f.id + "\t" + f.sepalLength + "\t" + f.sepalWidth + "\t" + f.petalLength +
				"\t" + f.petalWidth + "\t" + f.classifier + "\n");
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String nearestClassifier(Flower flower, int k) {
		ArrayList<Flower> distanceList = new ArrayList<Flower>();
		ArrayList<Flower> finalKList = new ArrayList<Flower>();
		double sepalLengthRange = 0;
		double sepalWidthRange = 0;
		double petalLengthRange = 0;
		double petalWidthRange = 0;

		double slrLow = Double.MAX_VALUE; double slrHigh = 0; double swrLow = Double.MAX_VALUE; double swrHigh = 0;
		double plrLow = Double.MAX_VALUE; double plrHigh = 0; double pwrLow = Double.MAX_VALUE; double pwrHigh = 0;

		for(Flower f : trainingSet) {
			if(f.sepalLength < slrLow) {
				slrLow = f.sepalLength;
			}
			if(f.sepalLength > slrHigh) {
				slrHigh = f.sepalLength;
			}
			if(f.sepalWidth < swrLow) {
				swrLow = f.sepalWidth;
			}
			if(f.sepalWidth > swrHigh) {
				swrHigh = f.sepalWidth;
			}
			if(f.petalLength < plrLow) {
				plrLow = f.petalLength;
			}
			if(f.petalLength > plrHigh) {
				plrHigh = f.petalLength;
			}
			if(f.petalWidth < pwrLow) {
				pwrLow = f.petalWidth;
			}
			if(f.petalWidth > pwrHigh) {
				pwrHigh = f.petalWidth;
			}
		}

		sepalLengthRange = slrHigh - slrLow;
		sepalWidthRange = swrHigh - swrLow;
		petalLengthRange = plrHigh - plrLow;
		petalWidthRange = pwrHigh - pwrLow;

		for(Flower f : trainingSet) {
			double firstBlock = 0;
			double secondBlock = 0;
			double thirdBlock = 0;
			double fourthBlock = 0;
			double fullBlock = 0;
			double finalDistance = 0;
			firstBlock = (Math.pow((flower.sepalLength - f.sepalLength), 2))/Math.pow(sepalLengthRange, 2);
			secondBlock = (Math.pow((flower.sepalWidth - f.sepalWidth), 2))/Math.pow(sepalWidthRange, 2);
			thirdBlock = (Math.pow((flower.petalLength - f.petalLength), 2))/Math.pow(petalLengthRange, 2);
			fourthBlock = (Math.pow((flower.petalWidth - f.petalWidth), 2))/Math.pow(petalWidthRange, 2);
			fullBlock = firstBlock + secondBlock + thirdBlock + fourthBlock;
			finalDistance = Math.sqrt(fullBlock);

			f.setDistance(finalDistance);
			distanceList.add(f);
		}
		Collections.sort(distanceList);
		for(int i = 0; i < k; i++) {
			finalKList.add(distanceList.get(i));
		}
		int irisSetosa = 0;
		int irisVersicolor = 0;
		int irisVirginica = 0;
		for(Flower f : finalKList) {
			if(f.getClassifier().equals("Iris-setosa")) {
				irisSetosa ++;
			}
			else if(f.getClassifier().equals("Iris-versicolor")) {
				irisVersicolor ++;
			}
			else if(f.getClassifier().equals("Iris-virginica")) {
				irisVirginica ++;
			}
		}
		if(irisSetosa >= irisVersicolor && irisSetosa >= irisVirginica){
			return "Iris-setosa";
		}
		else if(irisVersicolor >= irisSetosa && irisVersicolor >= irisVirginica) {
			return "Iris-versicolor";
		}
		else if(irisVirginica >= irisSetosa && irisVirginica >= irisVersicolor) {
			return "Iris-virginica";
		}
		return null;
	}
}
