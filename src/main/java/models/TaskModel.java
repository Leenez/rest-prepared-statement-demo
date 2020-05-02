package models;

public class TaskModel implements CombineModels{
	private int taskID;
	private String description;
	private Boolean taskDone;
	
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getTaskDone() {
		return taskDone;
	}
	public void setTaskDone(Boolean taskDone) {
		this.taskDone = taskDone;
	}
	
}
