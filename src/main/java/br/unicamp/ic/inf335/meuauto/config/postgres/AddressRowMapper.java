package br.unicamp.ic.inf335.meuauto.config.postgres;

import br.unicamp.ic.inf335.meuauto.entity.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AddressRowMapper implements RowMapper<Address> {

    private PointReader pointReader;

    public AddressRowMapper() {
        this.pointReader = new PointReader();
    }

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Address(
                UUID.fromString(rs.getString("id")),
                rs.getString("description"),
                rs.getString("postal_code"),
                rs.getString("google_id"),
                pointReader.read(rs.getBytes("location"))
        );
    }
}
