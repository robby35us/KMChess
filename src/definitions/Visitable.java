package definitions;


public interface Visitable {
	public Object accept(Visitor e);
}
