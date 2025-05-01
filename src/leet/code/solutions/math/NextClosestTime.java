package leet.code.solutions.math;

import java.util.HashSet;

public class NextClosestTime {

    public static void main(String[] args) {
        String time = "19:34";
        String nextClosestTime = nextClosestTime(time);
        System.out.println(nextClosestTime);

         time = "15:35";
         nextClosestTime = nextClosestTime(time);
        System.out.println(nextClosestTime);

        time = "23:59";
        nextClosestTime = nextClosestTime(time);
        System.out.println(nextClosestTime);
    }


    public static String nextClosestTime(String time) {

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < time.length(); i++){
            set.add(time.charAt(i) -'0');
        }

        int hours = Integer.parseInt(time.substring(0,2));
        int mins = Integer.parseInt(time.substring(3));

        while(true) {
            mins++;//add minute

            hours = (hours + mins/60) % 24;//new minute might have overlflowed valid time - so recalculate proper hour:min
            mins = mins % 60;

            if(set.contains(hours/10)
                    &&
                    set.contains(hours % 10)
                    &&
                    set.contains(mins / 10)
                    &&
                    set.contains(mins % 10)) {
                break;
            }
        }

        return String.format("%02d", hours) + ":" + String.format("%02d", mins);
    }
}
