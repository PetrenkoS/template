package util;

public enum Environment {
    DEVELOPMENT("dev"),
    STAGING("stg");

    private String environment;

    Environment(String environment) {this.environment = environment;}
    public String getValue() {return environment; };

    @Override
    public String toString() {return environment; }

    public static Environment toEnum(String value) {
        for (Environment e : values()) {
            if(e.getValue().equalsIgnoreCase(value)){
                return e;
            }
        }
        System.err.println(" [E] - "+ Browser.class.getName()+".toEnum() - invalid value: "+ value);
        throw new IllegalArgumentException();

    }

}
