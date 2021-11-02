package ch.gauthey.alain.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.gauthey.alain.bean.PatternFiles;
import ch.gauthey.alain.common.files.Files;

/**
 * Servlet implementation class JavaPattern
 */
@WebServlet("/tryIt")
public class TryIt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TryIt() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String familyPatternId = request.getParameter("familyPatternId");
        String patternId = request.getParameter("patternId");

        Files files = new Files(familyPatternId, patternId);
        List<PatternFiles> list = files.getFiles();

        HttpSession sess = request.getSession();
        sess.setAttribute("parentId", familyPatternId);
        sess.setAttribute("patternId", patternId);
        sess.setAttribute("PatternFiles", list);

        request.getRequestDispatcher("/tryIt.jsp").forward(request, response);
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


}
