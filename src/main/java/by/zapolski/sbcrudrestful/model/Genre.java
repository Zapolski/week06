package by.zapolski.sbcrudrestful.model;

public class Genre {
	
	private Long genId;
	private String genName;
	
	public Genre() {
		
	}
	
	public Genre(Long genId, String genName) {
		super();
		this.genId = genId;
		this.genName = genName;
	}
	public Long getGenId() {
		return genId;
	}
	public void setGenId(Long genId) {
		this.genId = genId;
	}
	public String getGenName() {
		return genName;
	}
	public void setGenName(String genName) {
		this.genName = genName;
	}
}
