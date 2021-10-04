package utils;

public class ActionResult {
    public boolean isSuccess;
    public String errorMessage;
    public static final ActionResult SuccessResponse = new ActionResult();

    public ActionResult() {
        this.isSuccess = true;
        this.errorMessage = "";
    }

    public ActionResult(String errorMessage) {
        this.isSuccess = false;
        this.errorMessage = errorMessage;
    }

}
