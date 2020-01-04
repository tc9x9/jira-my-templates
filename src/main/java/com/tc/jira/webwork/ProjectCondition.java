package com.tc.jira.webwork;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.ParameterUtils;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Scanned
public class ProjectCondition extends AbstractJiraCondition {

  private String validProject;
  private String currentProjectKey;

  private static final Logger log = LoggerFactory.getLogger(AddSubTask.class);

  @ComponentImport
  final PluginSettingsFactory pluginSettingsFactory;

  static final String PLUGIN_STORAGE_KEY = "com.tc.jira.adminui";


  @Inject
  public ProjectCondition(final PluginSettingsFactory pluginSettingsFactory) {
    this.pluginSettingsFactory = pluginSettingsFactory;
  }

  public Object getPluginKey(String key) {
      return pluginSettingsFactory.createGlobalSettings().get(key);
  }

  @Override
  public void init(Map params) throws PluginParseException {
    currentProjectKey = ParameterUtils.getStringParam(params, "currentProjectKey");
  }

  @Override
  public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
    validProject = (String) getPluginKey(PLUGIN_STORAGE_KEY + ".selectproject");
    boolean isSubtask = ((Issue) jiraHelper.getContextParams().get("issue")).isSubTask();
    currentProjectKey = jiraHelper.getProject().getKey();
    log.debug(">>> validProject    " + validProject);
    log.debug(">>> currentProject  " + currentProjectKey);
    return validProject != null && currentProjectKey != null && validProject.equals(currentProjectKey) && !isSubtask;
  }

}
