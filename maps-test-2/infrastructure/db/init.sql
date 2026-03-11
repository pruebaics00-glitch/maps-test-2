CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE IF NOT EXISTS pois (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    geom GEOMETRY(Point, 4326) NOT NULL
);

INSERT INTO pois (name, description, geom) VALUES
('Cristo de la Concordia', 'Estatua monumental de Jesucristo', ST_SetSRID(ST_MakePoint(-66.1345, -17.3840), 4326)),
('Plaza 14 de Septiembre', 'Plaza principal de Cochabamba', ST_SetSRID(ST_MakePoint(-66.1568, -17.3938), 4326)),
('Palacio Portales', 'Centro cultural y museo', ST_SetSRID(ST_MakePoint(-66.1492, -17.3742), 4326)),
('Estadio Félix Capriles', 'Estadio principal de la ciudad', ST_SetSRID(ST_MakePoint(-66.1620, -17.3820), 4326)),
('Parque Lincoln', 'Parque urbano y área verde', ST_SetSRID(ST_MakePoint(-66.1730, -17.3800), 4326));
