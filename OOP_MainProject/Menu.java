package OOP_MainProject.OOP_MainProject;

public interface Menu {
    abstract public void testMethod();
}

class testClass implements Menu{
    @Override
    public void testMethod() {
        System.out.println("This is a test string");
    }
}
