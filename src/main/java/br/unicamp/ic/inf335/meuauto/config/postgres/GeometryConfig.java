package br.unicamp.ic.inf335.meuauto.config.postgres;

import com.google.maps.DistanceMatrixApi;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.n52.jackson.datatype.jts.JtsModule;

@Configuration
class GeometryConfig {
    @Bean
    GeometryFactory geometryFactory() {
        return new GeometryFactory(new PrecisionModel(), 4326);
    }

    @Bean
    JtsModule jtsModule() {
        return new JtsModule();
    }
}
