import model.PresentationModel;

public class Starter {

    public static void main(String[] args) {
        PresentationModel model = new PresentationModel();
        model.init();

        System.out.println("Ciphertext: "+ model.getCipherText());

        System.out.println("Plaintext: "+model.decrypt());
    }

}
