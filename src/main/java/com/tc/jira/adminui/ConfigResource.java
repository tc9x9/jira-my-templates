package com.tc.jira.adminui;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import javax.inject.Inject;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Scanned
public class ConfigResource
{
    private static final Logger log = LoggerFactory.getLogger(ConfigResource.class);
    static final String PLUGIN_STORAGE_KEY = "com.tc.jira.adminui";

    @ComponentImport
    private final UserManager userManager;
    @ComponentImport
    private final PluginSettingsFactory pluginSettingsFactory;
    @ComponentImport
    private final TransactionTemplate transactionTemplate;

    @Inject
    public ConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory,
                          TransactionTemplate transactionTemplate)
    {
        this.userManager = userManager;
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.transactionTemplate = transactionTemplate;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(final Config config, @Context HttpServletRequest request)
    {
        String username = userManager.getRemoteUsername(request);
        if (username == null || !userManager.isSystemAdmin(username))
        {
            return Response.status(Status.UNAUTHORIZED).build();
        }

        transactionTemplate.execute(new TransactionCallback()
        {
            public Object doInTransaction()
            {
                PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".selectproject", config.getSelectProject());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype1", config.getIssueType1());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype2", config.getIssueType2());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype3", config.getIssueType3());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype4", config.getIssueType4());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype5", config.getIssueType5());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype6", config.getIssueType6());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype7", config.getIssueType7());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype8", config.getIssueType8());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype9", config.getIssueType9());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype10", config.getIssueType10());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype11", config.getIssueType11());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype12", config.getIssueType12());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype13", config.getIssueType13());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".issuetype14", config.getIssueType14());


                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask1", config.getSubtask1());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask2", config.getSubtask2());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask3", config.getSubtask3());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask4", config.getSubtask4());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask5", config.getSubtask5());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask6", config.getSubtask6());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask7", config.getSubtask7());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask8", config.getSubtask8());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask9", config.getSubtask9());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask10", config.getSubtask10());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask11", config.getSubtask11());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask12", config.getSubtask12());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask13", config.getSubtask13());
                pluginSettings.put(PLUGIN_STORAGE_KEY + ".subtask14", config.getSubtask14());
                return null;
            }
        });
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request)
    {
      String username = userManager.getRemoteUsername(request);
      if (username == null || !userManager.isSystemAdmin(username))
      {
        return Response.status(Status.UNAUTHORIZED).build();
      }

      return Response.ok(transactionTemplate.execute(new TransactionCallback()
      {
        public Object doInTransaction()
        {
          PluginSettings settings = pluginSettingsFactory.createGlobalSettings();

          Config config = new Config();
          config.setSelectProject((String) settings.get(PLUGIN_STORAGE_KEY + ".selectproject"));

          config.setSubtask1((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask1"));
          config.setSubtask2((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask2"));
          config.setSubtask3((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask3"));
          config.setSubtask4((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask4"));
          config.setSubtask5((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask5"));
          config.setSubtask6((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask6"));
          config.setSubtask7((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask7"));
          config.setSubtask8((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask8"));
          config.setSubtask9((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask9"));
          config.setSubtask10((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask10"));
          config.setSubtask11((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask11"));
          config.setSubtask12((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask12"));
          config.setSubtask13((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask13"));
          config.setSubtask14((String) settings.get(PLUGIN_STORAGE_KEY + ".subtask14"));


          config.setIssueType1((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype1"));
          config.setIssueType2((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype2"));
          config.setIssueType3((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype3"));
          config.setIssueType4((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype4"));
          config.setIssueType5((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype5"));
          config.setIssueType6((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype6"));
          config.setIssueType7((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype7"));
          config.setIssueType8((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype8"));
          config.setIssueType9((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype9"));
          config.setIssueType10((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype10"));
          config.setIssueType11((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype11"));
          config.setIssueType12((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype12"));
          config.setIssueType13((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype13"));
          config.setIssueType14((String) settings.get(PLUGIN_STORAGE_KEY + ".issuetype14"));

          return config;
        }
      })).build();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class Config
    {
      @XmlElement private String selectproject;

      @XmlElement private String subtask1;
      @XmlElement private String subtask2;
      @XmlElement private String subtask3;
      @XmlElement private String subtask4;
      @XmlElement private String subtask5;
      @XmlElement private String subtask6;
      @XmlElement private String subtask7;
      @XmlElement private String subtask8;
      @XmlElement private String subtask9;
      @XmlElement private String subtask10;
      @XmlElement private String subtask11;
      @XmlElement private String subtask12;
      @XmlElement private String subtask13;
      @XmlElement private String subtask14;

      @XmlElement private String issuetype1;
      @XmlElement private String issuetype2;
      @XmlElement private String issuetype3;
      @XmlElement private String issuetype4;
      @XmlElement private String issuetype5;
      @XmlElement private String issuetype6;
      @XmlElement private String issuetype7;
      @XmlElement private String issuetype8;
      @XmlElement private String issuetype9;
      @XmlElement private String issuetype10;
      @XmlElement private String issuetype11;
      @XmlElement private String issuetype12;
      @XmlElement private String issuetype13;
      @XmlElement private String issuetype14;

      public String getSelectProject()
      {
        return selectproject;
      }

      public void setSelectProject(String selectproject)
      {
        this.selectproject = selectproject;
      }

      public String getSubtask1()
      {
        return subtask1;
      }

      public void setSubtask1(String subtask)
      {
        this.subtask1 = subtask;
      }

      public String getSubtask2()
      {
        return subtask2;
      }

      public void setSubtask2(String subtask)
      {
        this.subtask2 = subtask;
      }

      public String getSubtask3()
      {
        return subtask3;
      }

      public void setSubtask3(String subtask)
      {
        this.subtask3 = subtask;
      }

      public String getSubtask4()
      {
        return subtask4;
      }

      public void setSubtask4(String subtask)
      {
        this.subtask4 = subtask;
      }

      public String getSubtask5()
      {
        return subtask5;
      }

      public void setSubtask5(String subtask)
      {
        this.subtask5 = subtask;
      }

      public String getSubtask6()
      {
        return subtask6;
      }

      public void setSubtask6(String subtask)
      {
        this.subtask6 = subtask;
      }

      public String getSubtask7()
      {
        return subtask7;
      }

      public void setSubtask7(String subtask)
      {
        this.subtask7 = subtask;
      }

      public String getSubtask8()
      {
        return subtask8;
      }

      public void setSubtask8(String subtask)
      {
        this.subtask8 = subtask;
      }

      public String getSubtask9()
      {
        return subtask9;
      }

      public void setSubtask9(String subtask)
      {
        this.subtask9 = subtask;
      }

      public String getSubtask10()
      {
        return subtask10;
      }

      public void setSubtask10(String subtask)
      {
        this.subtask10 = subtask;
      }

      public String getSubtask11()
      {
        return subtask11;
      }

      public void setSubtask11(String subtask)
      {
        this.subtask11 = subtask;
      }

      public String getSubtask12()
      {
        return subtask12;
      }

      public void setSubtask12(String subtask)
      {
        this.subtask12 = subtask;
      }

      public String getSubtask13()
      {
        return subtask13;
      }

      public void setSubtask13(String subtask)
      {
        this.subtask13 = subtask;
      }

      public String getSubtask14()
      {
        return subtask14;
      }

      public void setSubtask14(String subtask)
      {
        this.subtask14 = subtask;
      }

      // subtasktypes

      public String getIssueType1()
      {
        return issuetype1;
      }

      public void setIssueType1(String issuetype)
      {
        this.issuetype1 = issuetype;
      }

      public String getIssueType2()
      {
        return issuetype2;
      }

      public void setIssueType2(String issuetype)
      {
        this.issuetype2 = issuetype;
      }

      public String getIssueType3()
      {
        return issuetype3;
      }

      public void setIssueType3(String issuetype)
      {
        this.issuetype3 = issuetype;
      }

      public String getIssueType4()
      {
        return issuetype4;
      }

      public void setIssueType4(String issuetype)
      {
        this.issuetype4 = issuetype;
      }

      public String getIssueType5()
      {
        return issuetype5;
      }

      public void setIssueType5(String issuetype)
      {
        this.issuetype5 = issuetype;
      }

      public String getIssueType6()
      {
        return issuetype6;
      }

      public void setIssueType6(String issuetype)
      {
        this.issuetype6 = issuetype;
      }

      public String getIssueType7()
      {
        return issuetype7;
      }

      public void setIssueType7(String issuetype)
      {
        this.issuetype7 = issuetype;
      }

      public String getIssueType8()
      {
        return issuetype8;
      }

      public void setIssueType8(String issuetype)
      {
        this.issuetype8 = issuetype;
      }

      public String getIssueType9()
      {
        return issuetype9;
      }

      public void setIssueType9(String issuetype)
      {
        this.issuetype9 = issuetype;
      }

      public String getIssueType10()
      {
        return issuetype10;
      }

      public void setIssueType10(String issuetype)
      {
        this.issuetype10 = issuetype;
      }

      public String getIssueType11()
      {
        return issuetype11;
      }

      public void setIssueType11(String issuetype)
      {
        this.issuetype11 = issuetype;
      }

      public String getIssueType12()
      {
        return issuetype12;
      }

      public void setIssueType12(String issuetype)
      {
        this.issuetype12 = issuetype;
      }

      public String getIssueType13()
      {
        return issuetype13;
      }

      public void setIssueType13(String issuetype)
      {
        this.issuetype13 = issuetype;
      }

      public String getIssueType14()
      {
        return issuetype14;
      }

      public void setIssueType14(String issuetype)
      {
        this.issuetype14 = issuetype;
      }


    }
}
