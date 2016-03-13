package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.Console;

import java.util.Comparator;
import java.util.List;

public class PostsPrinter {

	private static final Comparator<Post> POST_COMPARATOR_BY_DATE =
			(o1, o2) -> {
				// Reverse order
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
				.sorted(POST_COMPARATOR_BY_DATE)
				.map(postFormatter::format)
				.forEachOrdered(console::printLine);
	}
}
