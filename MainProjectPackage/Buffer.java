package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Buffer {
    boolean messageBufferFull = false;
    boolean responseBufferFull = false;
    String CC;
    String response;

    public Buffer() {
        this.messageBufferFull = true;
    }

    public synchronized long send(String creditCard) {
        File ccFile = new File("TextFiles/BankCCInfo.txt");
        String verifiedCC, newCC;
        this.CC = creditCard;

        try {
            Scanner CCReader = new Scanner(ccFile);
            while (CCReader.hasNextLine()) {
                verifiedCC = CCReader.nextLine();
                if (verifiedCC.equals(CC)){
                    CCReader.close();
                    return (long) (Math.random() * 100000000);
                }
            }
            CCReader.close();
            return 0;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return 0;
    }
}

