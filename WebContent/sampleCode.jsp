<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
    <c:when test="${not empty PatternFiles and fn:length(PatternFiles) > 0}">
        <h3>Implementation</h3>
        <div class="bd-tryit"><button type="button" data-parentid="${parentId}" data-patternid="${patternId}" class="btn-tryit btn btn-secondary btn-sm">Try it</button></div>
        <div class="card card-body">
            <div>${implementation}</div>
            <c:forEach var="patternFile" items="${PatternFiles}">
                ${patternFile.description}
                <h5>${patternFile.fileName}</h5>
                <figure class="highlight">
                    <div class="bd-clipboard">
                        <button title="Copy to clipboard" class="btn-clipboard" data-pattern="${patternFile.id}" data-original-title="Copy to clipboard">Copy</button>
                    </div>
                    <pre id="${patternFile.id}"><c:out value="${patternFile.content}"/></pre>
                </figure>
            </c:forEach>
        </div>
    </c:when>
</c:choose>
<script>
  $('.btn-clipboard')._clipBoardCopy();
  $('.btn-tryit')._tryIt();
</script>