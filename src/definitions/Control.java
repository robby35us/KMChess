package definitions;

public interface Control {
	public void sendMessage();
	public void receiveMessage(Control sender);
	
}