package be.labofitness.labo_fitness.bll.exception;


public class LaboFitnessException extends RuntimeException {

    private int status;
    private Object message;

    public LaboFitnessException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        StackTraceElement elem = this.getStackTrace()[0];
        return this.getClass().getSimpleName() + " throw  in : "
                + elem.getFileName() + " in method : " + elem.getMethodName() + "at line number " + elem.getLineNumber()
                + " with message" + this.getMessage();

    }
}
