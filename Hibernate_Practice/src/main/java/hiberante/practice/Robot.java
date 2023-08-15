package hiberante.practice;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Robot {
	
	@Id
	int id;
	String name;
	int cost;
	
	Microcontroller microcontroller;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Robot [id=" + id + ", name=" + name + ", cost=" + cost + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Microcontroller getMicrocontroller() {
		return microcontroller;
	}
	public void setMicrocontroller(Microcontroller microcontroller) {
		this.microcontroller = microcontroller;
	}
}
