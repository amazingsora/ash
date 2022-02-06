package idv.ash.model.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the DEMODATA database table.
 * 
 */
@Entity
@NamedQuery(name="Demodata.findAll", query="SELECT d FROM Demodata d")
public class Demodata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private int seq;

	private String c1;

	private int c2;

	private String c3;

	@Lob
	private String c4;

	@Column(name="CREATED_NAME")
	private String createdName;

	@Column(name="CREATED_TIME")
	private String createdTime;

	@Column(name="UPDATED_NAME")
	private String updatedName;

	@Column(name="UPDATED_TIME")
	private String updatedTime;

	public Demodata() {
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getC1() {
		return this.c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return this.c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return this.c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return this.c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getCreatedName() {
		return this.createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedName() {
		return this.updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}

	public String getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "Demodata [seq=" + seq + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", createdName="
				+ createdName + ", createdTime=" + createdTime + ", updatedName=" + updatedName + ", updatedTime="
				+ updatedTime + "]";
	}
	
}