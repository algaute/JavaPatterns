package ch.gauthey.alain.common.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ch.gauthey.alain.bean.PatternFiles;

public class Files {

    private String familyPatternId;
    private String patternId;

    public Files() {
    }

    public Files(String familyPatternId, String patternId) {
        this.familyPatternId = familyPatternId;
        this.patternId = patternId;
    }

    public void setFamilyPatternId(String familyPatternId) {
        this.familyPatternId = familyPatternId;
    }

    public void setPatternId(String patternId) {
        this.patternId = patternId;
    }

    public List<PatternFiles> getFiles() {

        List<PatternFiles> list = new ArrayList<PatternFiles>();
        String filePath = Config.PROJECT_PATH + Config.JSON_PATH;
        String fileNameAndPath = filePath + this.familyPatternId + "Patterns.json";

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileNameAndPath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject pattern = (JSONObject) jsonObject.get(this.patternId);
            JSONArray files = (JSONArray) pattern.get("files");
            for (Object o : files) {
                JSONObject file = (JSONObject) o;
                String fileName = file.get("file").toString();
                String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                String description = file.get("description").toString();
                Boolean executable = Boolean.parseBoolean(file.get("executable").toString());

                list.add(new PatternFiles(baseName, fileName, description, executable, readJavaFile(fileName)));
            }

        } catch (Exception e) {
        }

        return list;
    }

    private String readJavaFile(String fileName) throws IOException {

        String filePath = Config.PROJECT_PATH + Config.JAVA_PATH;
        String fileNameAndPath = filePath + this.familyPatternId + "\\" + this.patternId + "\\" + fileName;

        BufferedReader br = new BufferedReader(new FileReader(fileNameAndPath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (line.trim().equals("")) {
                    sb.append(line);
                    sb.append("\n");
                } else {
                    line = filter(line);
                    if (!line.trim().equals("")) {
                        sb.append(line);
                        sb.append("\n");
                    }
                }
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public File writeClass(String path, String id, String code) {

        code = filter(code);
        File file = new File(path+"\\"+id+".java");
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            System.out.println(file.getPath());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(code);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private String filter(String result) {

        String content = result;

        // compilation de la regex
        Pattern p = Pattern.compile("package.*;");
        // création d'un moteur de recherche
        Matcher m = p.matcher(content);
        // lancement de la recherche de toutes les occurrences
        content = m.replaceAll("");

      //  p = Pattern.compile("import.*;");
      //  m = p.matcher(content);
      //  content = m.replaceAll("");

      //  p = Pattern.compile("public(.*(abstract).*)class");
      //  m = p.matcher(content);
      //  content = m.replaceAll("$2 class");

      //  p = Pattern.compile("public.*class");
      //  m = p.matcher(content);
      //  content = m.replaceAll("class");

      //  p = Pattern.compile("public.*interface");
      //  m = p.matcher(content);
      //  content = m.replaceAll("interface");

        return content;
    }

}
