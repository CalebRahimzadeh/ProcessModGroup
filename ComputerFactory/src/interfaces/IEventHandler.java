package interfaces;

public interface IEventHandler<T> {
		
		/**
		 * Classes that handle specific events defined by you.
		 * @param listener The listener you want to subscribe.
		 */
		void subscribe(T listener);
		void unsubscribe(T listener);
		void notifyListeners();
		
}
