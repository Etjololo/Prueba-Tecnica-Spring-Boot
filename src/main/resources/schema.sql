-- H2_Superheroes
DROP TABLE IF EXISTS H2_Superheroes;
CREATE TABLE H2_Superheroes (
ID IDENTITY PRIMARY KEY,
NOMBRE VARCHAR(50) NOT NULL
);

-- INSERT INTO H2_Superheroes
INSERT INTO H2_Superheroes (NOMBRE) VALUES ('Spiderman');
INSERT INTO H2_Superheroes (NOMBRE) VALUES ('Batman');
INSERT INTO H2_Superheroes (NOMBRE) VALUES ('Superman');
INSERT INTO H2_Superheroes (NOMBRE) VALUES ('Wonderwoman');
INSERT INTO H2_Superheroes (NOMBRE) VALUES ('Wolverine');