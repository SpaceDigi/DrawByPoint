import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by Mikolay
 * 14.10.2017 at 20:44
 * OKA2
 */
public class Point2D {


	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
	private int x;
	private int y;

	public Point2D(int x, int y){
		this.x = x;
		this.y = y;

	}

	public static int ccw(Point2D a, Point2D b, Point2D c){
		double res = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
		if(res>0)
			return 1;
		if(res<0)
			return -1;
		return 0;
	}
	public boolean equals(Object other){
		Point2D that=(Point2D) other;
		return that.x==this.x&&that.y==this.y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public void draw(){
		StdDraw.point(x,y);
	}
	public void drawTo(Point2D that){
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	public int compareTo(Point2D that){
		if(this.y<that.y)
			return -1;
		if(this.y>that.y)
			return 1;
		if(this.y==that.y){
			if(this.x<that.y)
				return -1;
			if(this.x>that.x)
				return 1;
		}
		return 0;
	}
	private class PolarOrder implements Comparator<Point2D> {
		@Override
		public int compare(Point2D p1, Point2D p2) {
			double dX1 = p1.x - x;
			double dY1 = p1.y - y;
			double dX2 = p2.x - x;
			double dY2 = p2.y - y;
			if      (dY1 >= 0 && dY2 < 0) return -1;
			if (dY2 >= 0 && dY1 < 0) return 1;
			if (dY1 == 0 && dY2 == 0) {
				if      (dX1 >= 0 && dX2 < 0) return -1;
				if (dX2 >= 0 && dX1 < 0) return 1;
				return  0;
			}
			return -ccw(Point2D.this, p1, p2);
		}
	}


}
