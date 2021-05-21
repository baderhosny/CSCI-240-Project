public class Message 
{

	private String dateReceived;
	private String text;
	private boolean checked;
	
	public Message() 
	{
		
	}

	public Message(String dateReceived, String text, boolean checked) 
	{
		this.dateReceived = dateReceived;
		this.text = text;
		this.checked = checked;
	}

	public String getDateReceived() 
	{
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) 
	{
		this.dateReceived = dateReceived;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	public boolean isChecked() 
	{
		return checked;
	}

	public void setChecked(boolean checked) 
	{
		this.checked = checked;
	}

	public String toString() {	//outputs the date and time of that message and then shows the message
		return "Message [dateReceived = " + dateReceived + ", text = " + text + "]";
	}
	
	
}
