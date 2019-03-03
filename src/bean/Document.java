package bean;

import java.util.Date;
import util.DateUtil;

public class Document {
	public int id;
	public String fileName;
	public String realName;
	public String type;
	public Date date;
	public int uid;
	public String userName;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setRealName(String realfileName) {
		this.realName = realfileName;
	}
	
	public void setType(String Type) {
		this.type = Type;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public String getType() {
		return type;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getUid() {
		return uid;
	}
}
