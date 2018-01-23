
import edu.princeton.cs.algs4.StdDraw;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Mikolay
 * 02.10.2017 at 17:00
 * OKA2
 */
public class SmartForceComputerVision {
    static int pair;
    static int[] x;
    static int[] y;

    public static void main(String[] args) {

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        //getDataFromFile("rs1423.txt");
        getDataFromFileSC("res/rs1423.txt");
        //BRute Force
        Point[] points = new Point[pair];
        for (int k = 0; k < pair; k++) {
            points[k] = new Point(x[k], y[k]);
            //StdDraw.point(x[k],y[k]);
        }
        long start = System.currentTimeMillis();
        for (int k = 0; k < pair - 3; k++) {
            for (int i = k + 1; i < pair - 2; i++) {
                Point p_i = points[i];
                double angle_0 = points[k].slopeTo(p_i);
                for (int q = i + 1; q < pair - 1; q++) {
                    Point p_q = points[q];
                    double angle_1 = points[k].slopeTo(p_q);
                    if (angle_1 == angle_0) {
                        for (int w = q + 1; w < pair; w++) {
                            Point p_w = points[w];
                            double angle_2 = points[k].slopeTo(p_w);
                            //
                            if (angle_0 == angle_1 && angle_0 == angle_2) {
                                System.out.print(points[k].toString() + " -> " + p_i.toString() + " -> ");
                                System.out.print(points[k].toString() + " -> " + p_q.toString() + " -> ");
                                System.out.println(points[k].toString() + " -> " + p_w.toString());
                                points[k].drawTo(p_i);
                                points[k].drawTo(p_q);
                                points[k].drawTo(p_w);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void getDataFromFileSC(String fileWay) {
        int[] x_p = null;
        int[] y_p = null;
        try {
            InputStream is = new FileInputStream(fileWay);
            Scanner sc = new Scanner(is);
            int n_pairs = 0;
            if (sc.hasNext())
                n_pairs = sc.nextInt();
            pair = n_pairs;
            x_p = new int[n_pairs];
            y_p = new int[n_pairs];
            int count = 0;
            while (sc.hasNext()) {
                x_p[count] = sc.nextInt();
                y_p[count++] = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        x = x_p;
        y = y_p;
    }
}
