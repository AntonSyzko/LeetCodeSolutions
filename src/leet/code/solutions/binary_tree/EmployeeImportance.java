package leet.code.solutions.binary_tree;

import java.util.*;

/*
https://leetcode.com/problems/employee-importance/description/

You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

employees[i].id is the ID of the ith employee.
employees[i].importance is the importance value of the ith employee.
employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.



Example 1:


Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
Example 2:


Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
Output: -3
Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.


Constraints:

1 <= employees.length <= 2000
1 <= employees[i].id <= 2000
All employees[i].id are unique.
-100 <= employees[i].importance <= 100
One employee has at most one direct leader and may have several subordinates.
The IDs in employees[i].subordinates are valid IDs.
 */
public class EmployeeImportance {

    public static void main(String[] args) {
          Employee employee1 = new Employee(1,5);
          Employee employee2 = new Employee(2,3);
          Employee employee3 = new Employee(3,3);
          Employee employee4 = new Employee(4,10);

          employee1.setSubordinates(List.of(employee2.id, employee3.id));

          employee2.setSubordinates(List.of(employee4.id));

          List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4);

          int totalImportance = getImportanceRec(employees, 1);

        System.out.println(totalImportance  );

    }

    private static int getImportanceRec(List<Employee> employees, int id) {
        Map<Integer, Employee> mapOfEmployees = new HashMap<>();

        for(Employee each : employees){
            mapOfEmployees.put(each.id, each);

        }

        return dfs(mapOfEmployees, id);
    }


    private static int  dfs(Map<Integer, Employee> employeeMap, int id) {

        Employee current = employeeMap.get(id);
        int totalImportance = current.importance;

        if(current.subordinates != null){

            for(int subordinateId : current.subordinates){

                totalImportance += dfs(employeeMap, subordinateId);
            }
        }

        return totalImportance;
    }


    private static int getImportanceSTack(List<Employee> employees, int id) {
        int totalImportance = 0;

        Map<Integer, Employee> mapOfEmployees = new HashMap<>();
        for(Employee each : employees){
            mapOfEmployees.put(each.id, each);

        }

        Stack<Employee> stack = new Stack<>();
        stack.push(mapOfEmployees.get(id));

        while (!stack.isEmpty()){

            Employee current = stack.pop();

            totalImportance += current.importance;

            if(current.subordinates != null){
                for(int subordinateId : current.subordinates){
                    stack.push(mapOfEmployees.get(subordinateId));
                }
            }
        }

        return totalImportance;
    }

    private  static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

       public Employee(int id, int importance) {
           this.id = id;
           this.importance = importance;
       }

       public List<Integer> getSubordinates() {
           return subordinates;
       }

       public void setSubordinates(List<Integer> subordinates) {
           this.subordinates = subordinates;
       }

       @Override
       public String toString() {
           return "Employee{" +
                   "id=" + id +
                   ", importance=" + importance +
                   ", subordinates=" + subordinates +
                   '}';
       }
   };
}
