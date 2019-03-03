package bean;

public class Privilege {
	public int id;
	public int uid;
	public int fid;
	public int docId;
	public int pemission;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public void setFid(int fid) {
		this.fid = fid;
	}
	
	public void setDocId(int docId) {
		this.docId = docId;
	}
	
	public void setPemission(int pemission) {
		this.pemission = pemission;
	}
	
	public int getUId() {
		return uid;
	}
	
	public int getFid() {
		return fid;
	}
	
	public int getDocId() {
		return docId;
	}
	
	public int getPemission() {
		return pemission;
	}
}

