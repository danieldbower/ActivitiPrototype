package activitiInAction;

import static org.junit.Assert.assertNotNull

import org.activiti.engine.ProcessEngine
import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.runtime.ProcessInstance
import org.grails.activiti.ActivitiService
import org.junit.Test

class SimpleProcessTest {
	
	ProcessEngine processEngine
	RuntimeService runtimeService
	RepositoryService repositoryService
	
	ActivitiService activitiService
	
	@Test
	void validateBaseActivitiEnvironment() {
		
		assert processEngine
		assert runtimeService
		assert repositoryService
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
				"simplebookorder");
		assertNotNull(processInstance.getId())
		
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
	}
	
	@Test
	void validatePluginEnvironment() {
		assert activitiService
		
		def processInstance = activitiService.startProcess([controller:'simplebookorder'])
		
		assertNotNull(processInstance.getId())
		
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
	}

}
