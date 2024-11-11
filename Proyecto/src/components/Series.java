package components;

public class Series extends Cinema{
	private int averageDuration; // Average length of a series
	private int seasons; // Seasons that a series has
	
	/** Default constructor
	 * 
	 */
	public Series() {
		super();
	}

	/** Creation of a new series
	 * @param img					Serie IMG
	 * @param title					Series title
	 * @param yr					Year of series
	 * @param rating				Series Rating
	 * @param review				Series review
	 * @param review_public			Series review public or private
	 * @param averageDuration		Average length of the series
	 * @param seasons				Seasons of the series
	 */
	public Series(String img, String title, int yr, int rating, String review, Boolean review_public, int averageDuration, int seasons) {
		super(img, title, yr, rating, review, review_public);
		this.averageDuration = averageDuration;
		this.seasons = seasons;
	}
	
	/** Creation of a simple serie
	 * @param img					Serie IMG
	 * @param title					Series title
	 * @param yr					Year of series
	 * @param rating				Series Rating
	 * @param averageDuration		Average length of the series
	 * @param seasons				Seasons of the series
	 */
	public Series(String img, String title, int yr, int rating, int averageDuration, int seasons) {
		super(img, title, yr, rating);
		this.averageDuration = averageDuration;
		this.seasons = seasons;
	}
	
	/** Get the average length of the series
	 * @return	Returns an integer of the number of minutes average
	 */
	public int getAverageDuration() {
		return averageDuration;
	}
	
	/** Sets the average length of a series
	 * @param averageDuration	Whole number in minutes of the duration
	 */
	public void setAverageDuration(int averageDuration) {
		this.averageDuration = averageDuration;
	}
	
	/** Get all the seasons of the series
	 * @return	Returns an integer of the number of seasons
	 */
	public int getSeasons() {
		return seasons;
	}
	
	/** Sets the seasons of the series
	 * @param seasons	Integer of the number of seasons
	 */
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return "Series [averageDuration=" + averageDuration + ", seasons=" + seasons + "]";
	}
}
