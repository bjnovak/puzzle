package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Shape {

    private final List<Point> points = new ArrayList<>();

    public int clear() {
        points.clear();
        return points.size();
    }

    public void generateRandom() throws Exception {
        for (int i = 0; i < 10; i++) {
            // 10 arbitrary attempts to make a random shape, or throw an exception
            if (random()) {
                return;
            }
        }
        throw new Exception();
    }

    public boolean addPoint(Point p) {
        // check if Point p already exists in List<Point> points
        if (points.stream().filter(point -> point.x() == p.x() && point.y() == p.y()).toList().size() > 0) {
            return false;
        }

        if (points.size() < 2) {
            // points 1 and 2 are added here before convex shape checking
            points.add(p);
            return true;
        } else {
            points.add(p);
            // check if new point is a convex shape
            if (isValidConvex()) {
                return true;
            } else {
                // remove point since the shape is not convex
                points.remove(points.size() - 1);
                return false;
            }
        }
    }

    public void print() {
        // prints all existing points in the current shape
        IntStream.range(0, points.size()).forEach(idx -> {
            Point c = points.get(idx);
            System.out.printf("%d:(%d,%d)%n", idx+1, c.x(), c.y());
        });
    }

    public boolean isValidConvex() {
        int n = points.size();

        if (n < 3) {
            // convex shape needs at least 3 points
            return false;
        }

        boolean hasPositiveCrossProduct = false;
        boolean hasNegativeCrossProduct = false;

        for (int i = 0; i < n; i++) {
            final int[] p1 = { points.get(i).x(), points.get(i).y() };
            final int[] p2 = { points.get((i + 1) % n).x(), points.get((i + 1) % n).y() };
            final int[] p3 = { points.get((i + 2) % n).x(), points.get((i + 2) % n).y() };

            // calculate cross product of vectors (p1, p2) and (p2, p3)
            final int crossProduct = crossProduct(p1, p2, p3);

            if (crossProduct > 0) {
                hasPositiveCrossProduct = true;
            } else if (crossProduct < 0) {
                hasNegativeCrossProduct = true;
            }

            // if both positive and negative cross products are found, the shape is not convex
            if (hasPositiveCrossProduct && hasNegativeCrossProduct) {
                return false;
            }
        }

        // if only one of positive or negative cross products is found, the shape is convex
        return true;
    }

    public boolean isWithinShape(Point point) {
        final int n = points.size();
        int windingNumber = 0;

        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);

            // check if the point is on the edge of the convex shape
            if (isPointOnSegment(p1, p2, point)) {
                return true;
            }

            // calculate winding number contribution of the edge (p1, p2)
            if (p1.y() <= point.y()) {
                if (p2.y() > point.y() && isLeftTurn(p1, p2, point)) {
                    windingNumber++;
                }
            } else {
                if (p2.y() <= point.y() && !isLeftTurn(p1, p2, point)) {
                    windingNumber--;
                }
            }
        }

        // point is inside the convex shape if windingNumber is nonzero
        return windingNumber != 0;
    }

    private boolean random() {
        final int minX = 0;
        final int maxX = 10;
        final int minY = 0;
        final int maxY = 10;

        final Random random = new Random();

        // generate 3-8 vertices
        final int totalPoints = random.nextInt(8 - 3 + 1) + 3;

        int n = 0;
        int attempts = 0;

        while (n < totalPoints) {
            // generate random x y point
            final int x = random.nextInt(maxX - minX + 1) + minX;
            final int y = random.nextInt(maxY - minY + 1) + minY;

            // determine if point can be added successfully to the shape
            if (addPoint(new Point(x, y))) {
                n++;
                attempts = 0;
            } else {
                attempts++;
                // limits the number of attempts to generate point n. meant to prevent edge cases of
                // infinite loop or a point might not exist to fit the existing shape to maintain convexity
                if (attempts == 500) {
                    points.clear();
                    // failed to generate a convex shape
                    return false;
                }
            }
        }

        // generated a convex shape
        return true;
    }

    // calculate cross product of points (p1, p2) and (p2, p3)
    private int crossProduct(int[] p1, int[] p2, int[] p3) {
        final int x1 = p2[0] - p1[0];
        final int y1 = p2[1] - p1[1];
        final int x2 = p3[0] - p2[0];
        final int y2 = p3[1] - p2[1];
        return x1 * y2 - x2 * y1;
    }

    // check if a point is on the line segment between p1 and p2
    private boolean isPointOnSegment(Point p1, Point p2, Point point) {
        return Math.min(p1.x(), p2.x()) <= point.x() &&
                point.x() <= Math.max(p1.x(), p2.x()) &&
                Math.min(p1.y(), p2.y()) <= point.y() &&
                point.y() <= Math.max(p1.y(), p2.y());
    }

    // check if the point p is to the left of the line segment defined by points p1 and p2
    private boolean isLeftTurn(Point p1, Point p2, Point p) {
        return ((p2.x() - p1.x()) * (p.y() - p1.y()) - (p2.y() - p1.y()) * (p.x() - p1.x())) > 0;
    }

}
