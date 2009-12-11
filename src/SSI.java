
public class SSI implements Comparable<SSI>{
	private int id;
	
	public SSI (int id) {
		this.id = id;
	}
	
	public int compareTo(SSI o) {
		if(this.id > o.id) {
			return 1;
		} else if(this.id < o.id){
			return -1;
		} else {
			return 0;
		}
	}
	
	public String toString() {
		return String.valueOf(id);
	}
}
