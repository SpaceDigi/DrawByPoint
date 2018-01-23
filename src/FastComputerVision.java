import edu.princeton.cs.algs4.StdDraw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Mikolay
 * 02.10.2017 at 17:00
 * OKA2
 */
public class FastComputerVision {
	static int pair;
	static int[] x;
	static int[] y;
	static Point[] points;
	public static void main(String[] args) {

		StdDraw.setCanvasSize(600, 600);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		getDataFromFileSC("res/rs1423.txt");
		System.out.println(pair);
		System.out.println(x.length);
		System.out.println(y.length);
		points = new Point[pair];
		for (int k = 0; k < pair; k++) {
			points[k] = new Point(x[k], y[k]);
			//StdDraw.point(x[k], y[k]);
		}

		//42917
		long start = System.currentTimeMillis();
		int count = 0;
		Point current;
		Point[] test;
		for(int k=0;k<points.length-1;k++) {
			current = points[k];
			test = shell(points,current);
			for(int i=1;i<points.length;i++){
				double angle = current.slopeTo(points[i]);
				for(int j=1;j<points.length;j++){
					if(angle==current.slopeTo(test[j])){
						count++;
						if(count>=3){
							current.drawTo(test[j]);
							System.out.print(current.toString()+" -> "+test[j]+" ");
						}
					}
				}
				count=0;
			}

			System.out.println();
		}

		System.out.println(System.currentTimeMillis()-start);

	}
	private static void getDataFromFileSC(String fileWay){
		int[] x_p=null;
		int[] y_p=null;
		try {
			InputStream is = new FileInputStream(fileWay);
			Scanner sc = new Scanner(is);
			int n_pairs=0;
			if(sc.hasNext())
				n_pairs = sc.nextInt();
			pair = n_pairs;
			x_p = new int[n_pairs];
			y_p = new int[n_pairs];
			int count =0;
			while(sc.hasNext()){
				x_p[count]=sc.nextInt();
				y_p[count++]=sc.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		x = x_p;
		y = y_p;
	}

	public static Point[] shell(Point[] a, Point per) {
		int interval = 1;
		while (interval<a.length/3)interval=3*interval+1;

		while (interval>=1){
			for(int k=interval;k<a.length;k++){
				for(int i=k;i>=interval;i-=interval){
					if(per.slopeTo(a[i])<per.slopeTo(a[i-interval]))
						exch(a,i,i-interval);
					else
						break;
				}
			}
			interval=interval/3;
		}
		return a;
	}
	private static void exch(Comparable[] a, int i, int j){
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
//204317