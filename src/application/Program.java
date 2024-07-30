package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String path = "c:\\Temp\\in.txt";

        List<Employee> employees = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            Double salaryIn = sc.nextDouble();
            System.out.println("\nEmail of people whose salary is more than "+ salaryIn + ":");

            List <Employee> filterEmployeePerSalary = employees.stream()
                    .filter(e -> e.getSalary()>= salaryIn)
                    .collect(Collectors.toList());

            Comparator<Employee> comp = (e1, e2) -> e1.getEmail().toUpperCase().compareTo(e2.getEmail().toUpperCase());
            filterEmployeePerSalary.sort(comp);

            System.out.println("\nEmail of people whose salary is more than "+ salaryIn + ":");

            for (Employee e: filterEmployeePerSalary) {
                System.out.println(e.getEmail());
            }

            Double sum = employees.stream().filter(e -> e.getName().charAt(0) =='M').map(e -> e.getSalary()).reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary of people whose name starts with 'M': " + sum);

        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}