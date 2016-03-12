package me.srodrigo.socialnetworkingkata.posts;

public class TimelinePostFormatter implements PostFormatter {

	private static final String POST_FORMAT = "%s (%s)";

	private final PastDateFormatter pastDateFormatter;

	public TimelinePostFormatter(PastDateFormatter pastDateFormatter) {
		this.pastDateFormatter = pastDateFormatter;
	}

	@Override
	public String format(Post post) {
		String dateFormatted = pastDateFormatter.format(post.date());
		return String.format(POST_FORMAT, post.message(), dateFormatted);
	}
}
