package MainProjectPackage;

public class BankHandler extends Thread{
    HandleOrder producer;
    long authorization;

    private Buffer buff;
    String CC;
    public BankHandler(Buffer buff, String CC){
        this.buff = buff;
        this.CC = CC;
    }

    @Override
    public void run() {
        authorization = buff.send(CC);
    }
}


