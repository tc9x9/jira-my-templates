package com.tc.jira.webwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.issue.IssueService.IssueResult;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.exception.CreateException;
// import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import java.util.*;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

// @ Scanned says consider this class as part of something that the scanner
// build time should consider (like @Coponent / @Inject but without any other
// side effects) and  @ComponentImport says OSGI import this component from
// outside and make it available to me

@Scanned
public class AddSubTask extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(AddSubTask.class);
    static final String PLUGIN_STORAGE_KEY = "com.tc.jira.adminui";

    private Long parentIssueId;
    @ComponentImport
    private final IssueManager issueManager;
    @ComponentImport
    private final SubTaskManager subTaskManager;
    @ComponentImport
    private final JiraAuthenticationContext authenticationContext;
    @ComponentImport
    private final IssueFactory issueFactory;
    private String issueId;

    @ComponentImport
    final PluginSettingsFactory pluginSettingsFactory;

    @Inject
    public AddSubTask(final IssueManager issueManager,
                      final IssueFactory issueFactory,
                      final SubTaskManager subtaskManager,
                      final JiraAuthenticationContext authenticationContext,
                      final PluginSettingsFactory pluginSettingsFactory) {
      this.issueManager = issueManager;
      this.issueFactory  = issueFactory;
      this.subTaskManager = subtaskManager;
      this.authenticationContext = authenticationContext;
      this.pluginSettingsFactory = pluginSettingsFactory;
    }

    public Object getPluginKey(String key) {
        return pluginSettingsFactory.createGlobalSettings().get(key);
    }

    private Issue createSubTask(String subtaskTitle,String subtaskIssuetype,MutableIssue parentIssueObject) {

      log.debug(">>> createSubTask    " + subtaskTitle);
      log.debug(">>> parrentIssueKey  " + issueId);
      log.debug(">>> subtask type     " + subtaskIssuetype);
      log.debug(">>> creating subtask :) ");

      String replacedsubTaskTitle = subtaskTitle.replace("@@PARENT@@",parentIssueObject.getKey() + " " + parentIssueObject.getSummary());
      MutableIssue newSubTask = issueFactory.getIssue();
      newSubTask.setSummary(replacedsubTaskTitle);
      log.debug(">>> createSubTask (replaced)   " + replacedsubTaskTitle);
      newSubTask.setProject(parentIssueObject.getProject());
      newSubTask.setProjectObject(parentIssueObject.getProjectObject());
      newSubTask.setIssueTypeId(subtaskIssuetype);
      newSubTask.setParentId(parentIssueObject.getId());

      Issue subTask;
      try {
        subTask = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), newSubTask);
        subTaskManager.createSubTaskIssueLink(parentIssueObject, newSubTask, authenticationContext.getLoggedInUser());
        log.debug(">>> " + subTask.getId().toString());
      } catch (CreateException ce) {
        log.debug(">>> " + ce);
            throw new IssueCreationException();
      }
      return subTask;
    }

    @Override
    public String doExecute() throws Exception {
     String subtask;

     MutableIssue parentIssueObject =
       issueManager.getIssueObject(Long.valueOf(issueId).longValue());

     ArrayList<STask> subtaskList = new ArrayList<STask>();
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask1"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype1")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask2"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype2")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask3"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype3")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask4"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype4")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask5"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype5")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask6"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype6")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask7"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype7")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask8"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype8")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask9"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype9")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask10"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype10")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask11"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype11")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask12"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype12")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask13"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype13")));
     subtaskList.add(new STask((String) getPluginKey(PLUGIN_STORAGE_KEY + ".subtask14"),(String) getPluginKey(PLUGIN_STORAGE_KEY + ".issuetype14")));
     subtaskList.forEach((subt) -> {
        if  ( subt.subtask!= null && !subt.subtask.isEmpty()) {
            log.debug(subt.subtask);
            log.debug(subt.issuetype);
            Issue subTask = createSubTask(subt.subtask,subt.issuetype,parentIssueObject);
        }
      }
     );

     return returnMsgToUser("/browse/" + parentIssueObject.getKey(), "subtasks created", MessageType.SUCCESS, true, null);
     // return SUCCESS;
    }
    public String getIssueId() {
      return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }
    public class IssueCreationException extends RuntimeException {
    }

    public class STask {
        public String subtask, issuetype;
        public STask(String subtask, String issuetype) {
        this.subtask = subtask;
        this.issuetype = issuetype;
      }
    }
}
