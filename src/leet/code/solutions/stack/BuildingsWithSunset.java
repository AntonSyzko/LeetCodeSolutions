package leet.code.solutions.stack;

import java.util.*;

/*
houses with taller height can see sunset, houses to the ease ( smaller cannot )
 */
public class BuildingsWithSunset {
    public static void main(String[] args) {
        Deque<BuildingWithHeight> housesWithSunset = examineBuildingsWithSunset(List.of(1,2,6,4).listIterator());
        System.out.println(housesWithSunset);
    }


    public static Deque<BuildingWithHeight> examineBuildingsWithSunset(Iterator<Integer> sequence){
        int buildingIdx = 0;

        Deque<BuildingWithHeight> buildingsWithSunset = new ArrayDeque<>();//stack

        while (sequence.hasNext()) {
            int currentBuildingHeight = sequence.next();

            //if current is taller
            if(!buildingsWithSunset.isEmpty() &&
                currentBuildingHeight >= buildingsWithSunset.peek().height){//get last
                //found taller, remove last ( it cannot see sunset anymore )
                buildingsWithSunset.pop();//remove last
            }
            //not taller just add
            buildingsWithSunset.addLast(new BuildingWithHeight(buildingIdx++,currentBuildingHeight));
        }

        return buildingsWithSunset;
    }

    private static class BuildingWithHeight {
        public Integer id;
        public Integer height;

        public BuildingWithHeight(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }

        @Override
        public String toString() {
            return "BuildingWithHeight{" +
                "id=" + id +
                ", height=" + height +
                '}';
        }
    }
}

