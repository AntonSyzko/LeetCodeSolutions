package leet.code.solutions.math;

public class RectangleIntersect {

    public static void main(String[] args) {
        Rectangle R1 = new Rectangle(2,4,3,5);
        Rectangle R2 = new Rectangle(3,6,7,1);
        Rectangle intersection = intersectRectangle(R1, R2);
        System.out.println(intersection);


    }

    private static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {
        if (!isRectangleIntersect(R1, R2)) {
            return new Rectangle(0, 0, -1, -1);
        }

        return new Rectangle(Math.max(R1.x, R2.x),
                            Math.max(R1.y, R2.y),
                            Math.min((R1.x + R1.width), (R2.x + R2.width)),
                            Math.min((R1.y + R1.heigth), (R2.y + R2.heigth)));
    }

    private static boolean isRectangleIntersect(Rectangle R1, Rectangle R2) {
        return  R1.x <= R2.x + R2.width &&
                R1.x + R1.width >= R2.x &&
                R1.y <= R2.y + R2.heigth &&
                R1.y + R1.heigth >= R2.y;
    }


    private static class Rectangle {
        int x, y, width, heigth;

        public Rectangle(int x, int y, int width, int heigth) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.heigth = heigth;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", heigth=" + heigth +
                '}';
        }
    }
}
