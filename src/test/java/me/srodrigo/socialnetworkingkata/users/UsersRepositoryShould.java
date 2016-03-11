package me.srodrigo.socialnetworkingkata.users;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UsersRepositoryShould {

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
		User user = usersRepository.findByUsername("username");

		assertThat(user, is(User.NULL));
	}
}
