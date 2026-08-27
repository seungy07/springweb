package example.day36_day02_0826.활동6.model.dto;

public class ListDto {
    private String number;
    private int n;

    public ListDto(){}
    public ListDto(String number, int n) {
        this.number = number;
        this.n = n;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    @Override
    public String toString() {
        return "ListDto [number=" + number + ", n=" + n + "]";
    }

    
    
    
}
