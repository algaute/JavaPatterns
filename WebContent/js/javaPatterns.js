$(document).ready(function() {
  console.log("document ready occurred!");

  $('#behavioral')._loadFamilyPatterns();
  $('#creational')._loadFamilyPatterns();
  $('#structural')._loadFamilyPatterns();
  $('#J2EE')._loadFamilyPatterns();
});

(function($) {
  $.fn._loadFamilyPatterns = function(e) {
    this.on("click", function(e) {
      console.log('a ' + e.type + ' event occured on ' + this.id);
      removePatternDescription();
      if ($(this).hasClass("collapsed")) {
        $("nav#navBarLeft div a:not(.collapsed)").addClass("collapsed");
        $("nav#navBarLeft div ul.collapse.show").removeClass("show"); // Hide previous item
        $("nav#navBarLeft div ul#collapse_" + this.id).empty(); // Empty current item to reload
        $(this)._loadPatterns(e, this, "json/" + this.id + "Patterns.json");
      }
    })
    return this;
  };
})(jQuery);

(function($) {
  $.fn._loadPatterns = function(e, familyPattern, source) {
    console.log('load JSON file from ' + source);

    var menuItems = [];
    var detailItems = [];

    $.getJSON(source, function(data) {
      $.each(data, function(key, val) {
        menuItems.push(getMenuItem(familyPattern, val));
        detailItems.push(getDetailItem(val));
      });
    }).done(function() {
      $(menuItems.join("")).appendTo("nav#navBarLeft div ul#collapse_" + familyPattern.id);
      $(detailItems.join("")).appendTo("main div#description");
    }).fail(function(jqxhr, textStatus, error) {
      var err = textStatus + ", " + error;
      console.log("Request Failed: " + err);
      alert("Request Failed: " + err);
    });

    return this;
  }
})(jQuery);

removePatternDescription = function() {
  $("main div#description").empty();
  $("main div#sample").empty();
  return true;
};

changePatternDescription = function(e, familyPatternId, patternId) {
  $("main div#description div.show").removeClass("show");
  $("main div#sample").empty();

  console.log('load pattern from ' + familyPatternId + " " + patternId);

  $.post("/JavaPatterns/pattern", {
    familyPatternId : familyPatternId,
    patternId : patternId
  }).done(function(data) {
    $(data).appendTo("main div#sample");
  }).fail(function(jqxhr, textStatus, error) {
    var err = textStatus + ", " + error;
    console.log("Request Failed: " + err);
    alert("Request Failed: " + err);
  });

  return true;
};

getMenuItem = function(familyPattern, val) {
  return "<li class=\"subnav-item\"><a class=\"nav-link\" data-toggle=\"collapse\" href=\"#collapse_" + val.id
      + "\" role=\"button\" aria-expanded=\"false\" aria-controls=\"collapse_" + val.id + "\" onclick=\"changePatternDescription(event, '" + familyPattern.id + "' ,'" + val.id
      + "');\">" + val.name + "</a></li>";
};

getDetailItem = function(val) {
  return "<div class=\"collapse\" id=\"collapse_" + val.id + "\">" + "<h1>" + val.name + "</h1>" + "<div class=\"card card-body\">" + val.description + "</div>" + "</div>";
};

function copyTextToClipboard(text) {
  var textArea = document.createElement("textarea");

  //
  // *** This styling is an extra step which is likely not required. ***
  //
  // Why is it here? To ensure:
  // 1. the element is able to have focus and selection.
  // 2. if element was to flash render it has minimal visual impact.
  // 3. less flakyness with selection and copying which **might** occur if
  // the textarea element is not visible.
  //
  // The likelihood is the element won't even render, not even a flash,
  // so some of these are just precautions. However in IE the element
  // is visible whilst the popup box asking the user for permission for
  // the web page to copy to the clipboard.
  //

  // Place in top-left corner of screen regardless of scroll position.
  textArea.style.position = 'fixed';
  textArea.style.top = 0;
  textArea.style.left = 0;

  // Ensure it has a small width and height. Setting to 1px / 1em
  // doesn't work as this gives a negative w/h on some browsers.
  textArea.style.width = '2em';
  textArea.style.height = '2em';

  // We don't need padding, reducing the size if it does flash render.
  textArea.style.padding = 0;

  // Clean up any borders.
  textArea.style.border = 'none';
  textArea.style.outline = 'none';
  textArea.style.boxShadow = 'none';

  // Avoid flash of white box if rendered for any reason.
  textArea.style.background = 'transparent';

  textArea.value = text;

  document.body.appendChild(textArea);
  textArea.focus();
  textArea.select();

  try {
    var successful = document.execCommand('copy');
    var msg = successful ? 'successful' : 'unsuccessful';
    console.log('Copying text command was ' + msg);
  } catch (err) {
    console.log('Oops, unable to copy');
  }

  document.body.removeChild(textArea);
}

(function($) {
  $.fn._clipBoardCopy = function(e) {
    this.on("click", function(e) {
      console.log('Copy ' + $(this).data("pattern") + ' to clipboard');
      var codeId = $(this).data("pattern");

      copyTextToClipboard(document.getElementById(codeId).innerHTML);
    })
    return this;
  }
})(jQuery);

(function($) {
  $.fn._tryIt = function(e) {
    this.on("click", function(e) {
      var parentId = $(this).data("parentid");
      var patternId = $(this).data("patternid");

      console.log('Try ' + parentId + " " + patternId);

      $.post("/JavaPatterns/tryIt", {
        familyPatternId : parentId,
        patternId : patternId
      }).done(function(data) {
        $("main div#sample").empty();
        $(data).appendTo("main div#sample");
      }).fail(function(jqxhr, textStatus, error) {
        var err = textStatus + ", " + error;
        console.log("Request Failed: " + err);
        alert("Request Failed: " + err);
      });

    })
    return this;
  }
})(jQuery);

(function($) {
  $.fn._run = function(e) {
    this.on("click", function(e) {
      var parentId = $(this).data("parentid");
      var patternId = $(this).data("patternid");
      var myClasses = $('pre.divtext').map(function() {
        var content = {
          id : this.id,
          code : $(this).text(),
          executable : $(this).data("executable")
        };
        return content;
      }).get();

      console.log('Try to run ' + parentId + " " + patternId);

      $.post("/JavaPatterns/run", {
        familyPatternId : parentId,
        patternId : patternId,
        contents : JSON.stringify(myClasses)
      }).done(function(data) {
        $("main div#sample div#runResult").empty();
        $(data).appendTo("main div#sample div#runResult");

        $('html, body').animate({
          scrollTop : $(".result-group").offset().top
        }, 2000);

      }).fail(function(jqxhr, textStatus, error) {
        var err = textStatus + ", " + error;
        console.log("Request Failed: " + err);
        alert("Request Failed: " + err);
      });

    })
    return this;
  }
})(jQuery);