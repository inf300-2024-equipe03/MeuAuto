package br.unicamp.ic.inf335.meuauto.repository;

import br.unicamp.ic.inf335.meuauto.config.postgres.AddressRowMapper;
import br.unicamp.ic.inf335.meuauto.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Address address) {
        var query = "insert into addresses(id, description, postal_code, google_id, location) values ('"
                + address.getId().toString()
                + "','"
                + address.getDescription()
                + "','"
                + address.getPostalCode()
                + "','"
                + address.getGoogleId()
                + "',"
                + " ST_SetSRID(ST_MakePoint("
                + address.getLocation().getCoordinate().y
                + ","
                + address.getLocation().getCoordinate().x
                + "), 4326))";


        jdbcTemplate.update(query);
    }

    public Address findById(UUID id) {
        var query = "SELECT a.* FROM addresses a WHERE id = '" + id.toString() + "\'";

        var result = jdbcTemplate.query(query,  new AddressRowMapper()).stream().findFirst().orElse(null);

        return result;
    }

    public List<Address> findNearbyPlaces(Address address, int offset, int limit) {
        var query = "SELECT a.* FROM addresses a ORDER BY ST_DistanceSpheroid(a.location, ST_SetSRID(ST_MakePoint("
                + address.getLocation().getCoordinate().y + ", "
                +  address.getLocation().getCoordinate().x + "),4326))"
                + " offset " +  offset + " limit " + limit;

        var result = jdbcTemplate.query(query,  new AddressRowMapper());

        return result;
    }
}
