(function ($) {

  var templatesapi = AJS.contextPath() + "/rest/mytemplates/1.0/";

  function updateConfig() {
    var restdata = '{ ' +
          '"selectproject": "' + AJS.$("#selectproject").attr("value") + '",' +
          '"subtask1": "' + AJS.$("#subtask1").attr("value")   + '",' +
          '"subtask2": "' + AJS.$("#subtask2").attr("value")   + '",' +
          '"subtask3": "' + AJS.$("#subtask3").attr("value")   + '",' +
          '"subtask4": "' + AJS.$("#subtask4").attr("value")   + '",' +
          '"subtask5": "' + AJS.$("#subtask5").attr("value")   + '",' +
          '"subtask6": "' + AJS.$("#subtask6").attr("value")   + '",' +
          '"subtask7": "' + AJS.$("#subtask7").attr("value")   + '",' +
          '"subtask8": "' + AJS.$("#subtask8").attr("value")   + '",' +
          '"subtask9": "' + AJS.$("#subtask9").attr("value")   + '",' +
          '"subtask10": "'+ AJS.$("#subtask10").attr("value")  + '",' +
          '"subtask11": "'+ AJS.$("#subtask11").attr("value")  + '",' +
          '"subtask12": "'+ AJS.$("#subtask12").attr("value")  + '",' +
          '"subtask13": "'+ AJS.$("#subtask13").attr("value")  + '",' +
          '"subtask14": "'+ AJS.$("#subtask14").attr("value")  + '",' +
          '"issuetype1": "' + AJS.$("#issuetype1").attr("value") + '",' +
          '"issuetype2": "' + AJS.$("#issuetype2").attr("value") + '",' +
          '"issuetype3": "' + AJS.$("#issuetype3").attr("value") + '",' +
          '"issuetype4": "' + AJS.$("#issuetype4").attr("value") + '",' +
          '"issuetype5": "' + AJS.$("#issuetype5").attr("value") + '",' +
          '"issuetype6": "' + AJS.$("#issuetype6").attr("value") + '",' +
          '"issuetype7": "' + AJS.$("#issuetype7").attr("value") + '",' +
          '"issuetype8": "' + AJS.$("#issuetype8").attr("value") + '",' +
          '"issuetype9": "' + AJS.$("#issuetype9").attr("value") + '",' +
          '"issuetype10": "'+ AJS.$("#issuetype10").attr("value")+ '",'  +
          '"issuetype11": "'+ AJS.$("#issuetype11").attr("value")+ '",'  +
          '"issuetype12": "'+ AJS.$("#issuetype12").attr("value")+ '",'  +
          '"issuetype13": "'+ AJS.$("#issuetype13").attr("value")+ '",'  +
          '"issuetype14": "'+ AJS.$("#issuetype14").attr("value")+ '"'  +
          '}';
          console.log(restdata);
    AJS.$.ajax({
        url: templatesapi,
        type: "PUT",
        contentType: "application/json",
        data: restdata,
        processData: false,
        success: function(data) {
           JIRA.Messages.showSuccessMsg(AJS.I18n.getText("Configuration saved"));
        },

    });
  }

  $(document).ready(function() {
    var readconfig = function (callback) {
      $.ajax({
          url: templatesapi,
          dataType: "json"
      }).done(function(config) {
        // console.log("Config readed [START]:");
        globalconfig = config
        // console.log(globalconfig);
        // console.log("Config readed [STOP]:");
        callback();
      });
    }

    var jiraapi = AJS.contextPath() + "/rest/api/2/project/";
    var globalconfig = null;

    var reloadsubtask = function()  {
      console.log("Reloadsubtask")
      var projectKey = $("#selectproject").val();
      console.log("Project Key: " + projectKey);
      if (projectKey) {
        $.ajax({
            url: jiraapi + projectKey,
            dataType: 'json',
        }).done(function(result) {
          console.log("API project readed")
          console.log(result);
          for (var i = 1; i <= 14; i++) {
            $("#issuetype" + i.toString()).empty();
            $("#subtask" + i.toString()).val(globalconfig['subtask' + i.toString()]);
          }
          $.each(result.issueTypes, function(idx, type) {
            if (type.subtask == true) {
              for (var i = 1; i <= 14;i++) {
                var itype = "issuetype" + i.toString();
                if (globalconfig[itype] == type.id) {
                  $("#" + itype).append('<option selected="true" value="' + type.id + '">' + type.name + '</option>');
                } else {
                  $("#" + itype).append('<option value="' + type.id + '">' + type.name + '</option>');
                }

                $("#" + itype).auiSelect2();
              }
            }
          });
        });

      }
    }
    var reload = function() {
      console.log("Reload start [Start]");
      // console.log(globalconfig);
      // console.log("Reload start [End]")

      $.ajax({
        url:  jiraapi,
        dataType: "json"
      }).done(function(result) {
          console.log("API readed");
          $.each(result, function(idx, project) {
              if (globalconfig.selectproject == project.key) {
                $("#selectproject").append('<option selected="true" value="' + project.key + '">' + project.name + '</option>')
              } else {
                $("#selectproject").append('<option value="' + project.key + '">' + project.name + '</option>')
              }
          })
          $("#selectproject").auiSelect2();
          reloadsubtask();
          $("#selectproject").on('change', reloadsubtask);

      });
    };
    $("#admin").submit(function(e) {
        e.preventDefault();
        updateConfig();
    });
    readconfig(reload);
  })
})(AJS.$ || jQuery);
