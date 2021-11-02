package ch.gauthey.alain.web;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ch.gauthey.alain.bean.PatternFiles;
import ch.gauthey.alain.common.files.Config;
import ch.gauthey.alain.common.files.Files;

/**
 * Servlet implementation class JavaPattern
 */
@WebServlet("/pattern")
public class JavaPattern extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public JavaPattern() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String familyPatternId = request.getParameter("familyPatternId");
        String patternId = request.getParameter("patternId");
        String implementation = readImplementation(familyPatternId, patternId);

        Files files = new Files(familyPatternId, patternId);
        List<PatternFiles> list = files.getFiles();

        HttpSession sess = request.getSession();
        sess.setAttribute("parentId", familyPatternId);
        sess.setAttribute("patternId", patternId);
        sess.setAttribute("PatternFiles", list);
        sess.setAttribute("implementation", implementation);
        request.getRequestDispatcher("/sampleCode.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public String readImplementation(String familyPatternId, String patternId)  {

        String filePath = Config.PROJECT_PATH + Config.JSON_PATH;
        String fileNameAndPath = filePath + familyPatternId + "Patterns.json";
        String implementation="";

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileNameAndPath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject pattern = (JSONObject) jsonObject.get(patternId);
            implementation=pattern.get("implementation").toString();
        } catch (Exception e) {
        }

        return implementation;
    }

}
