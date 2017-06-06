package models;

public abstract class Resource {
	private Consumer consumer;
	public Resource() {
		this.consumer = null;
	}
	
	public void aquire(Consumer consumer){
		if(this.consumer == null){
			this.consumer  = consumer;
		}
	}
	
	public void release(Consumer consumer){
		if(isLockedByConsumer(consumer)){
			this.consumer = null;
		}
	}
	
	public boolean isLockedByConsumer(Consumer consumer) {
		return this.consumer.equals(consumer);
	}
}
