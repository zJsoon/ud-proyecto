package components;

public class Cinema {
	private String imgCover; // Name of the photo of the series
	private String title; // Cinema title
	private String review; // Cinema review
	private int yr; // Year of Cinema
	private int rating; // Cinema Rating
	private Boolean review_public; // Cinema review is public
	
	/** Default constructor
	 * 
	 */
	public Cinema() {
		super();
	}
	
	/** Creation of a new cinema
	 * @param title				Title
	 * @param yr				Year
	 * @param rating			Rating
	 */
	public Cinema (String imgCover, String title, int yr, int rating, String review, Boolean review_public) {
		super();
		this.imgCover = imgCover;
		this.title = title;
		this.yr = yr;
		this.rating = rating;
		this.review = review;
		this.review_public = review_public;
	}
	
	public Cinema (String imgCover, String title, int yr, int rating) {
		super();
		this.imgCover = imgCover;
		this.title = title;
		this.yr = yr;
		this.rating = rating;
	}
	
	/**	Get the name of the image
	 * @return	Returns the name of the image plus its extension.
	 */
	public String getImgCover() {
		return imgCover;
	}
	
	/**	Rename the photo
	 * @param imgCover	New name of the photo
	 */
	public void setImgCover(String imgCover) {
		this.imgCover = imgCover;
	}
	
	/**	Get the name of the title
	 * @return	Name of the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**	Rename the title
	 * @param title	New name of the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**	Get the int of the year
	 * @return	int of the year
	 */
	public int getYr() {
		return yr;
	}
	
	/**	Change year
	 * @param yr	New year
	 */
	public void setYr(int yr) {
		this.yr = yr;
	}
	
	/**	Get the int of the rating
	 * @return	int of the rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**	Change rating
	 * @param rating	New rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**	Get the string of the review
	 * @return	string of the review
	 */
	public String getReview() {
		return review;
	}
	
	/**	Change review
	 * @param review	New review
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**	Get the boolean of the review_public
	 * @return	boolean of the review_public
	 */
	public Boolean getReview_public() {
		return review_public;
	}
	
	/**	Change review_public
	 * @param review_public	Review public? true/false
	 */
	public void setReview_public(Boolean review_public) {
		this.review_public = review_public;
	}

	@Override
	public String toString() {
		return "Cinema [title=" + title + ", review=" + review + ", yr=" + yr + ", rating=" + rating
				+ ", review_public=" + review_public + "]";
	}

}
