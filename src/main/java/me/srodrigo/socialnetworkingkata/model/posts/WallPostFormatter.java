package me.srodrigo.socialnetworkingkata.model.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.PastDateFormatter;

public class WallPostFormatter implements PostFormatter {

	private static final String POST_FORMAT = "%s - %s (%s)";

	private final PastDateFormatter pastDateFormatter;

	public WallPostFormatter(PastDateFormatter pastDateFormatter) {
		this.pastDateFormatter = pastDateFormatter;
	}

	@Override
	public String format(Post post) {
		String formattedDate = pastDateFormatter.format(post.date());
		return String.format(POST_FORMAT, post.username(), post.message(), formattedDate);
	}
}
