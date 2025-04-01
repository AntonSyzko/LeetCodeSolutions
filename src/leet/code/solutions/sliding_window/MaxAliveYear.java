package leet.code.solutions.sliding_window;

/*
given persons dob and death

{
[1990,2012] , [1991, 1993] , [1990, 1999 ] , [ 1992, 2010] , [1992, 2010]

start year = 1990
end = 2012

outut = 1992

if person dies on year - death is counted as next uyear
so if died at 1993 - death year is 1994, he was alive in 1993
}

 */
public class MaxAliveYear {

    public static void main(String[] args) {


        Person[] persons = {
                new Person(1990,2012),
                new Person(1991, 1993),
                new Person(1990, 1999),
                new Person(1992, 2010),
                new Person(1992, 2010)
          };

        int startYear = 1990;
        int endYear = 2012;

        int aliveYearMax = maxAliveYear(persons, startYear, endYear);
        System.out.println(aliveYearMax);
    }

    private static int maxAliveYear(Person[] persons, int startYear, int endYear) {

        int[] aliveDelats = getPopulationDeltas(persons, startYear, endYear);
        int maxAliveYEar = getMaxAliveYear(aliveDelats);
        return startYear + maxAliveYEar;
    }

    private static int getMaxAliveYear(int[] aliveDelats) {
        int maxPersonsAlive = 0;
        int maxAliveYearRes = 0;
        int currentPersonsAliveAtYEar = 0;

        for (int year = 0; year < aliveDelats.length; year++) {

            currentPersonsAliveAtYEar += aliveDelats[year];

            if(currentPersonsAliveAtYEar > maxPersonsAlive){
                maxPersonsAlive = currentPersonsAliveAtYEar;
                maxAliveYearRes = year;
            }

        }
        return maxAliveYearRes;
    }

    private static int[] getPopulationDeltas(Person[] persons, int startYear, int endYear) {

        // +1 cause we need to account if died on current year - we mark death on next year
        //+ 1 more cause length os zero based
        int yearsCount = endYear - startYear + 2;

        int [] aliveDeltas = new int[yearsCount];

        for( Person persona : persons ) {
            int birthIndex = persona.birth - startYear;// get year od this person alive
            int deathIndex = persona.death - startYear;// get year od this person's death

            aliveDeltas[birthIndex] += 1;//increment as person was alive
            aliveDeltas[deathIndex] -= 1;//decrement as on this index year we know person died
        }

        return aliveDeltas;
    }


    static class Person{
        public int birth;
        public int death;

        public Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }
    }
}
