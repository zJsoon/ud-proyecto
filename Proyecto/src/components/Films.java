package components;

public class Films extends Cinema{
	private int duration; // Length of the movie
	
	/** Default constructor
	 * 
	 */
	public Films() {
		super();
	}
	
	/** Creation of a new film
	 * @param title				Films title
	 * @param yr				Year of the film
	 * @param rating			Films rating
	 * @param review			Films review
	 * @param review_public		Films review public or private
	 * @param duration			Length of the film
	 */
	public Films(String title, int yr, int rating, String review, Boolean review_public, int duration) {
		super(title, yr, rating, review, review_public);
		this.duration = duration;
	}

	/** Get  length of the films
	 * @return	Returns an integer of the number of the film
	 */
	public int getDuration() {
		return duration;
	}

	/** Sets the  length of a films
	 * @param duration	Whole number in minutes of the duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Films [duration=" + duration + "]";
	}
}
