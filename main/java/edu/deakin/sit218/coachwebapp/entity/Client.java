package edu.deakin.sit218.coachwebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "client")
public class Client {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idclient")
	protected int id;

	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Pattern(regexp = "^[A-Za-z]*", message = "Answer must be one word.")
	
	@Column(name = "answer")
	public String answer;
	
	@Column(name = "question")
	protected String question;
	
	@Column(name = "area")
	public String area;

	public Client () {
	}
	
	public Client(String question, String answer, String area) {
		setQuestion(question);
		setAnswer(answer);
		setArea(area);
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return "client = [name: "+getQuestion()+", answer: "+getAnswer()+", area: "+getArea()+"]";
	}

	
}