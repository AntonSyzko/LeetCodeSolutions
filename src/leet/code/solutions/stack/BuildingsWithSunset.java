package leet.code.solutions.stack;

import java.util.*;

/*
houses with taller height can see sunset, houses to the ease ( smaller cannot )
 */
public class BuildingsWithSunset {

    public static void main(String[] args) {
        Stack<BuildingWithHeight> housesWithSunset = examineBuildingsWithSunset(List.of(1,2,6,4));
        System.out.println(housesWithSunset);
    }

    public static Stack<BuildingWithHeight> examineBuildingsWithSunset(List<Integer> buildings) {

        Stack<BuildingWithHeight> stack = new Stack<>();//stack res

        int buildingIdx = 0;

        for(int currBuilding: buildings){

            //if current is taller
            if(!stack.isEmpty() &&
                    currBuilding >= stack.peek().height){//get last from stak
                //found taller, remove last ( it cannot see sunset anymore )
                stack.pop();//remove last from stak
            }

            //not taller just add
            stack.addLast(new BuildingWithHeight(buildingIdx, currBuilding));
            buildingIdx++;
        }

        return stack;
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

