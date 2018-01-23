import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

	public final Comparator<Point> SLOPE_ORDER=new Comparator<Point>(){
		@Override
		public int compare(Point p1, Point p2) {
			if(slopeTo(p1)<slopeTo(p2))
				return 0;
			return 1;
		}
	};

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {
		double grad=0;
		if(that.y==this.y&&this.x==that.x)
			return Double.NEGATIVE_INFINITY;

		if(that.x==this.x)
			return Double.NEGATIVE_INFINITY;

		if(that.y==this.y)
			return Double.POSITIVE_INFINITY;


		if(that.x!=this.x) {
			grad = (that.y - this.y) /(double)(that.x - this.x);
		}
		return grad;
	}

	public int compareTo(Point that) {
		if((this.y<that.y||this.y==that.y)&&this.x<that.x)
			return 0;
		return 1;
	}



	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Comparator<Point> getSLOPE_ORDER() {
		return SLOPE_ORDER;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}