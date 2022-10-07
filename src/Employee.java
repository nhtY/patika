
public class Employee {
    // employee's name
    private String name;

    // employee's salary
    private double salary;

    // weekly work hours of the employee
    private double workHours;

    // the year which the employee started the work
    private int hireYear;

    // Constructor method for employee
    public Employee(String name, double salary, double workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }

    // Getters and Setters:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    // other methods:

    // calculate the tax applied to the salary
    private double tax(){

        if (this.salary > 1000){
            return this.salary * 0.03;
        }

        return 0;
    }

    // calculate bonus amount according to extra working hours
    private double bonus(){

        if (this.workHours > 40){
            double extraHours = this.workHours - 40;
            return 30 * extraHours;
        }
        return 0;
    }

    // raise the employee's salary according to how many years s/he's been working.
    public void raiseSalary(){

        // apply the addition to the salary without bonus.
        this.salary -= bonus();

        // calculate how many years s/he has been working
        int years = 2021 - this.hireYear;

        if (years < 10){
            // a raise of 5%:
            this.salary *= 1.05;

        }else if (10 <= years && years < 20){
            // a raise of 10%:
            this.salary *= 1.10;
        }else{
            // a raise of 15%:
            this.salary *= 1.15;
        }
        this.salary = this.salary - tax();
    }
}
