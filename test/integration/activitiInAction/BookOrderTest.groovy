package activitiInAction;

import static org.junit.Assert.assertNotNull

import org.activiti.engine.IdentityService
import org.activiti.engine.ProcessEngine
import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.TaskService
import org.activiti.engine.runtime.ProcessInstance
import org.activiti.engine.task.Task
import org.grails.activiti.ActivitiService
import org.junit.Test

class BookOrderTest {
	
	ProcessEngine processEngine
	RuntimeService runtimeService
	RepositoryService repositoryService
	TaskService taskService
	
	IdentityService identityService
	
	ActivitiService activitiService
	
	@Test
	void baseSimpleOrder() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
				"simplebookorder");
		assertNotNull(processInstance.getId())
		
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
	}
	
	@Test
	void pluginSimpleOrder() {
		def processInstance = activitiService.startProcess([controller:'simplebookorder', 'isbn':'abcd1234'])
		
		assertNotNull(processInstance.getId())
		
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
	}
	
	@Test
	void baseFullOrder() {
		identityService.setAuthenticatedUserId('kermit')

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
				"bookorder", ['isbn':'abcd1234']);
		assertNotNull(processInstance.getId())
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
		
		List<Task> tasksForKermit = taskService.createTaskQuery().
				taskCandidateUser('kermit').list()
		assert tasksForKermit.size() == 1
	
		Task task1 = tasksForKermit[0]
		println "found task ${task1.name}"
		assert task1.name == "Work on order"
		
		taskService.complete(task1.id)
	}
	
	@Test
	void pluginFullOrder() {
		identityService.setAuthenticatedUserId('kermit')

		def processInstance = activitiService.startProcess(
				[controller:'bookorder', 'isbn':'abcd1234'])
		assertNotNull(processInstance.getId())
		println("id " + processInstance.getId() + " " + processInstance
				.getProcessDefinitionId())
		
		
		def allTasks = activitiService.findAllTasks(['max':10])
		println allTasks.size()
		
		
		
		def tasksForKermit = activitiService.findAssignedTasks(
			['username':'kermit',
				'max':10])
		assert tasksForKermit.size() == 1
	
		Task task1 = tasksForKermit[0]
		println "found task ${task1.name}"
		assert task1.name == "Work on order"
		
		activitiService.completeTask(task1.id)
	}

}
