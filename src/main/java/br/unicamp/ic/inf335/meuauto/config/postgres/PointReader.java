package br.unicamp.ic.inf335.meuauto.config.postgres;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.impl.PackedCoordinateSequenceFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Component;

@Component
public class PointReader {
    private static final int SRID = 4326;
    private final WKTReader reader;
    private final WKBReader wkbReader;
    private final GeometryFactory gf;

    public PointReader() {
        PrecisionModel pm = new PrecisionModel(PrecisionModel.FLOATING);
        gf = new GeometryFactory(pm, SRID);
        wkbReader = new WKBReader(gf);
        reader = new WKTReader(gf);
    }

    public Point read(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            Geometry geometry;
            try {
                geometry = wkbReader.read(bytes);
                return convert3Dto2D(geometry);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    // need the point to have only 2 measurements, but not three
    private Point convert3Dto2D(Geometry g3D) {
        Coordinate geomCoord = g3D.getCoordinate().copy();
        CoordinateSequence seq = new PackedCoordinateSequenceFactory().create(1, 2);
        seq.setOrdinate(0, CoordinateSequence.X, geomCoord.x);
        seq.setOrdinate(0, CoordinateSequence.Y, geomCoord.y);
        return gf.createPoint(seq);
    }
}
