package part1;

public class Flower implements Comparable<Flower> {

	public int id;
	public double sepalLength;
	public double sepalWidth;
	public double petalLength;
	public double petalWidth;
	public String classifier = "";
	public double distance;

	public Flower(int id, double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
		this.id = id;
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
	}

	public String getClassifier() {
		return this.classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Flower f) {
		if(this.distance > f.distance) {
			return 1;
		}
		if(this.distance < f.distance) {
			return -1;
		}
		else{
			return 0;
		}
	}
}
