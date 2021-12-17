package br.com.lstcode;


import br.com.lstcode.model.Credentials;
import br.com.lstcode.model.UserGroup;
import br.com.lstcode.services.JiraUserGroupService;
import br.com.lstcode.utils.ReadExcel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class AddMultiplesUserToJiraApplication implements CommandLineRunner {

	private final ReadExcel readExcel;

	private final JiraUserGroupService jiraUserGroupService;

	public AddMultiplesUserToJiraApplication(ReadExcel readExcel, JiraUserGroupService jiraUserGroupService) {
		this.readExcel = readExcel;
		this.jiraUserGroupService = jiraUserGroupService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AddMultiplesUserToJiraApplication.class, args);
		SpringApplication application = new SpringApplication(AddMultiplesUserToJiraApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<UserGroup> userGroupList = readExcel.readExcelUserGroup("./user-group.xlsx");
		Credentials credentials = readExcel.readExcelUserPassword("./user-group.xlsx");
		userGroupList.forEach(userGroup -> {
			jiraUserGroupService.insertUserInGroup(userGroupList, credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
		});

	}
}
