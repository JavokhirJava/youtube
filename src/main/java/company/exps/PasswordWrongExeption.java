package company.exps;

public class PasswordWrongExeption extends  RuntimeException{
    public PasswordWrongExeption(String message) {
        super(message);
    }
}
