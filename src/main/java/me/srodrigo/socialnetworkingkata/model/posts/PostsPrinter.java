package me.srodrigo.socialnetworkingkata.model.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.Console;

import java.util.Comparator;
import java.util.List;

public class PostsPrinter {

	private static final Comparator<Post> BY_DATE_REVERSE_ORDER =
			(o1, o2) -> {
				if (o1.date() > o2.date()) return -1;
				else if (o1.date() < o2.date()) return 1;
				return 0;
			};

	private final PostFormatter postFormatter;
	private final Console console;

	public PostsPrinter(PostFormatter postFormatter, Console console) {
		this.postFormatter = postFormatter;
		this.console = console;
	}

	public void print(List<Post> posts) {
		posts.stream()
				.sorted(BY_DATE_REVERSE_ORDER)
				.map(postFormatter::format)
				.forEachOrdered(console::printLine);
	}

	public void log(List<Post> userPosts) {
	}
}
