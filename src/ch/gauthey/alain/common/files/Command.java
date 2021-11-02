package ch.gauthey.alain.common.files;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Command {

    private String[] command;
    private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:SSS");
    private List<String> input;
    private List<String> error;

    public Command() {
        input=new ArrayList<String>();
        error=new ArrayList<String>();
    }

    public Command(String[] command) {
        super();
        this.command = command;
    }

    public void setCommand(String[] command) {
        this.command = command;
    }

    public List<String> getResult() {
        return input;
    }

    public List<String> getError() {
        return error;
    }

    public boolean hasError() {
        return error.size()>0;
    }

    public void execute() {
        if (command != null) {
            this.execute(command);
        }
    }

    public void execute(String[] command) {
        try {
            log(Arrays.toString(command));
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);

            input = readCommandResult(process.getInputStream());
            error = readCommandResult(process.getErrorStream());

            logOutput(input, "");
            logOutput(error, "Error: ");

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> readCommandResult(InputStream inputStream) {
        List<String> list = new ArrayList<String>();

        //new Thread(() -> {
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                synchronized (this) {
                    list.add(scanner.nextLine());
                }
            }
            scanner.close();
        //}).start();

        return list;
    }

    private void logOutput(List<String> input, String prefix) {
        for (String in : input) {
            log(prefix + in);
        }
    }

    private synchronized void log(String message) {
        System.out.println(format.format(new Date()) + ": " + message);
    }
}
