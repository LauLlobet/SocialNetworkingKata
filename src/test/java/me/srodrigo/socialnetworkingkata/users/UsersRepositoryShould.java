package me.srodrigo.socialnetworkingkata.users;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UsersRepositoryShould {

	private static final String USERNAME = "username";
	private static final User USER = user(USERNAME);

	private UsersRepository usersRepository;

	@Before
	public void setUp() {
		usersRepository = new UsersRepository();
	}

	@Test public void
	be_empty_after_being_created() {
		assertThat(usersRepository.findAll().size(), is(0));
	}

	@Test public void
	return_null_user_when_username_does_not_exist() {
		User user = usersRepository.findByUsername(USERNAME);

		assertThat(user, is(User.NULL));
	}

	@Test public void
	find_an_existing_user_by_username() {
		usersRepository.create(USERNAME);

		User user = usersRepository.findByUsername(USERNAME);

		assertThat(user, is(USER));
	}

	@Test public void
	return_created_user() {
		User user = usersRepository.create(USERNAME);

		assertThat(user, is(USER));
	}

	private static User user(String username) {
		return new User(username);
	}
}
