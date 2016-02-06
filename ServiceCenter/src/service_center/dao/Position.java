package service_center.dao;

import javax.persistence.*;

@Entity
public class Position {
	@Id
	private int accessLevel;
	private String positionJob;

	public Position() {
		}

	public Position(int accessLevel, String positionJob) {
		this.accessLevel = accessLevel;
		this.positionJob = positionJob;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getPositionJob() {
		return positionJob;
	}

	public void setPositionJob(String positionJob) {
		this.positionJob = positionJob;
	}
}
