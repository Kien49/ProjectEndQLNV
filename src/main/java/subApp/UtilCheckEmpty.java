package subApp;

public class UtilCheckEmpty {
    public  boolean isEmpty(String input){
        return input == null || input.trim().isEmpty();
    }
    public void checkEmpty (String input){
        if(isEmpty(input)){
            System.out.println("Không được để trống");
        }
    }




}
