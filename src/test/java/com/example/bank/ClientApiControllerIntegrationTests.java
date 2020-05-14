package com.example.bank;

import com.example.bank.api.ClientsApi;
import com.example.bank.model.Client;
import com.example.bank.service.ClientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApiControllerIntegrationTests {

	@Autowired
	private ClientsService clientsService;

	@Autowired
	private ClientsApi api;

	private Client client1;
	private Client client2;
	private Client client3;

	@Before
	public void setUp() {
		client1 = new Client("Boris", "Johnson", "bjohnson@yahoo.com");
		client2 = new Client("Natasha", "Romanoff", "nromanoff@yahoo.com");
		client3 = new Client("Brad", "Pitt", "bpitt@yahoo.com");
	}

	@Test
	public void createClientTest() {
		ResponseEntity<Map> responseEntity = api.createClient(client1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void createExistingClientTest() {
		api.createClient(client2);
		ResponseEntity<Map> responseEntity = api.createClient(client2);
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	public void getClientTest() {
		ResponseEntity<Map> responseEntity1 = api.createClient(client3);
		Long id = Long.parseLong(Objects.requireNonNull(responseEntity1.getBody()).get("id").toString());

		ResponseEntity<Client> responseEntity2 = api.getClientById(id);
		assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
		assertThat(responseEntity2.getBody().getId(), is(id));
	}

}
