package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview", "Info about the card");
        context.setVariable("message",message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("companyInfo", adminConfig.getCompanyName()+" "+adminConfig.getEmail());
        return templateEngine.process("mail/created-trello-card-mail",context);
    }
    public String buildDbEmail() {
        String taskSelector;
        long size = taskRepository.count();
        if (size > 1) {
            taskSelector=" tasks";
        } else {
            taskSelector=" task";
        }
        Context context = new Context();
        context.setVariable("preview", "Info about number of tasks");
        context.setVariable("user", "Kodilla User");
        context.setVariable("message","Currently in database you got: " + size + taskSelector);
        context.setVariable("is_admin", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("companyInfo", adminConfig.getCompanyName()+" "+adminConfig.getEmail());
        return templateEngine.process("mail/dbMail",context);
    }

}
