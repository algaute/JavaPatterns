package ch.gauthey.alain.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ch.gauthey.alain.common.files.Command;
import ch.gauthey.alain.common.files.File;
import ch.gauthey.alain.common.files.Files;

/**
 * Servlet implementation class JavaPattern
 */
@WebServlet("/run")
public class RunIt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public RunIt() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String result = "";
        Command command;

        String JSONContent = request.getParameter("contents");
        try {
            File dir = prepareWorkingDir();
            List<File> classes = parseAndWriteTestClasses(dir, JSONContent);

            List<String> cmd=new ArrayList<String>();
            cmd.add("c:\\eri\\OLY-IDE-v2.4\\java\\jdk7\\bin\\javac");
            cmd.add("-cp");
            cmd.add(dir.getPath());
            for (File file : classes) {
                //cmd.add(file.getParent() + " " + file.getPath());
                cmd.add(file.getPath());
            }

            // compile
            command = new Command(cmd.toArray(new String[cmd.size()]));
            command.execute();

            if (command.hasError()) {
                // get error
                for (String res : command.getError()) {
                    result += res + "\n";
                }
            }

            for (File file : classes) {

                if (file.isExecutable()) {
                    cmd=new ArrayList<String>();
                    cmd.add("java");
                    cmd.add("-cp");
                    cmd.add(dir.getPath());
                    cmd.add(file.getName().replace(".java", ""));
                    // execute
                    command = new Command(cmd.toArray(new String[cmd.size()]));
                    command.execute();
                    if (!command.hasError()) {
                        // get result
                        for (String res : command.getResult()) {
                            result += res + "\n";
                        }
                    } else {
                        // get error
                        for (String res : command.getError()) {
                            result += res + "\n";
                        }
                    }
                }
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        // display result
        HttpSession sess = request.getSession();
        sess.setAttribute("result", result);

        request.getRequestDispatcher("/runIt.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private List<File> parseAndWriteTestClasses(File dir, String JSONContent) throws Exception {

        List<File> testClasses = new ArrayList<File>();

        JSONParser parser = new JSONParser();
        JSONArray jsonClasses = (JSONArray) parser.parse(JSONContent);
        for (Object o : jsonClasses) {
            JSONObject classz = (JSONObject) o;

            String id = classz.get("id").toString();
            String code = classz.get("code").toString();
            Boolean executable = Boolean.parseBoolean(classz.get("executable").toString());

            // filter and write the code
            File file = new Files().writeClass(dir.getPath(), id, code);
            testClasses.add(file);

            if (executable) {
                file.setIsExecutable(true);
            }
        }

        return testClasses;
    }

    private File prepareWorkingDir() throws Exception {
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        File dir = new File(tempDir + randomUUIDString + "\\");
        dir.mkdir();
        if (!dir.exists())
            throw new Exception("Temp Directory not created");
        return dir;
    }

}
