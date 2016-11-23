package a1_p04_arn_vb;


/**
 * AttrVertex ist eine Funktion um Vertex mit Attributwert
 * zu speichern
 */

public class AttrVertex
{
	 private String name;
	 private int value;

     public AttrVertex(){
		this.value = 0;
		this.name = "";
	}

 	public AttrVertex(String name){
		this.value = 0;
		this.name = name;
	}
     
	public AttrVertex(String name, int value){
		this.value = value;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		//result = prime * result + value;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttrVertex other = (AttrVertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		//if (value != other.value)
		//	return false;
		return true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setAttr(String name, int value) {
		this.name = name;
		this.value = value;
	}

    public String toString()
    {
        return name;
    }
}
