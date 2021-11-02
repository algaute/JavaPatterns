<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
    <div class="col-sm-6">
        <div class="form-group">
            <label for="Classes">Classes:</label>
            <c:forEach var="patternFile" items="${PatternFiles}">
                <div>
                    <pre class="divtext" id="${patternFile.id}" data-executable="${patternFile.executable}" contentEditable=true><c:out value="${patternFile.content}"/></pre>
                    <c:if test="${patternFile.executable==true}">
                        <div class="bd-run">
                            <button type="button" data-parentid="${parentId}" data-patternid="${patternId}" class="btn-run btn btn-secondary btn-sm">Run</button>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
    <div id="runResult" class="col-sm-6"></div>
</div>
<script>
  $('.btn-run')._run();
</script>
