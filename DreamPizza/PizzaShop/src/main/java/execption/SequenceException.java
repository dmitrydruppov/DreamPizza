package execption;

/**
 * Created by Dmytro_Druppov on 10/1/2016.
 */
public class SequenceException extends RuntimeException{

    private String errorMsg;

    public SequenceException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
