package activitiInAction;

import static org.junit.Assert.assertNotNull

import org.activiti.engine.IdentityService
import org.activiti.engine.ProcessEngine
import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.TaskService
import org.grails.activiti.ActivitiService
import org.junit.Test

class EnvironmentSanityTest {
	
	ProcessEngine processEngine
	RuntimeService runtimeService
	RepositoryService repositoryService
	TaskService taskService
	
	IdentityService identityService

	ActivitiService activitiService
	
	@Test
	void validateBaseActivitiEnvironment() {
		assert processEngine
		assert runtimeService
		assert repositoryService
		assert taskService
	}
	
	@Test
	void validateIdentityEnvironment() {
		assert identityService
		
		println identityService.class.name
	}
	
	@Test
	void validatePluginEnvironment() {
		assert activitiService
	}
	
	

}
