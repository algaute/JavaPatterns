package ch.gauthey.alain.common.files;

public class Arrays extends org.apache.catalina.tribes.util.Arrays {

    public static String toString(String[] data) {
        StringBuilder sb=new StringBuilder();

        for (String str: data) {
            sb.append(str).append(" ");
        }

        return sb.toString().trim();
    }

}
