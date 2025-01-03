-- INSERCIÓN DE DATOS DE PRUEBA EN LA BASE DE DATOS

-- INSERCIÓN DE RESTAURANTES
-- Crear una tabla temporal para insertar los datos nuevos
CREATE TABLE
    TEMP_RESTAURANT (
        CIF VARCHAR(10),
        NAME VARCHAR(100),
        IMAGE_URL VARCHAR(255),
        STREET VARCHAR(100),
        LOCALITY VARCHAR(100)
    );

-- Insertar los datos nuevos en la tabla temporal
INSERT INTO
    TEMP_RESTAURANT (CIF, NAME, IMAGE_URL, STREET, LOCALITY)
VALUES
    (
        'D45678901',
        'Restaurante Ruiz de Luna',
        'https://i.postimg.cc/JhdW2V8r/ruiz-De-Luna-Restaurant.jpg',
        'Calle Carnicerías, 10',
        'Talavera de la Reina'
    ),
    (
        'E56789012',
        'Restaurante El Albero',
        'https://i.postimg.cc/2yWmWHPz/el-Albero-Restaurant.png',
        'Avenida de Toledo, 15',
        'Talavera de la Reina'
    ),
    (
        'F67890123',
        'Restaurante La Caprichosa',
        'https://i.postimg.cc/d1pwLMNH/la-Caprichosa-Restaurant.jpg',
        'Plaza del Pan, 1',
        'Talavera de la Reina'
    ),
    (
        'G78901234',
        'Restaurante El Olivo',
        'https://i.postimg.cc/pV5P3Tyg/el-Olivo-Restaurant.png',
        'Calle Olivo, 22',
        'Talavera de la Reina'
    ),
    (
        'H89012345',
        'Restaurante La Parrilla',
        'https://i.postimg.cc/5NwbNRBg/la-Parrilla-Restaurant.jpg',
        'Calle Mayor, 5',
        'Talavera de la Reina'
    ),
    (
        'I90123456',
        'Restaurante El Mirador',
        'https://i.postimg.cc/yYq7KHZW/el-Mirador-Restaurant.jpg',
        'Calle Mirador, 8',
        'Talavera de la Reina'
    ),
    (
        'J01234567',
        'Restaurante La Terraza',
        'https://i.postimg.cc/mgBRcyVs/la-Terraza-Restaurant.jpg',
        'Calle Terraza, 12',
        'Talavera de la Reina'
    ),
    (
        'K12345678',
        'Restaurante El Rincón',
        'https://i.postimg.cc/kgsCWRht/el-Rincon-Restaurant.png',
        'Calle Rincón, 3',
        'Talavera de la Reina'
    ),
    (
        'L23456789',
        'Restaurante La Plaza',
        'https://i.postimg.cc/d00Y6QzN/la-Plaza-Restaurant.jpg',
        'Plaza Mayor, 2',
        'Talavera de la Reina'
    ),
    (
        'M34567890',
        'Restaurante El Molino',
        'https://i.postimg.cc/FzwYnxTd/el-Molino-Restaurant.jpg',
        'Calle Molino, 7',
        'Talavera de la Reina'
    ),
    (
        'Z98765432',
        'Restaurante El Paso',
        'https://i.postimg.cc/nrXxgbWT/el-Paso-Restaurant.jpg',
        'Calle Lisboa, s/n',
        'Talavera de la Reina'
    );

-- Insertar los datos únicos desde la tabla temporal a la tabla principal
INSERT INTO
    RESTAURANT (CIF, NAME, IMAGE_URL, STREET, LOCALITY)
SELECT
    TEMP.CIF,
    TEMP.NAME,
    TEMP.IMAGE_URL,
    TEMP.STREET,
    TEMP.LOCALITY
FROM
    TEMP_RESTAURANT TEMP
WHERE
    NOT EXISTS (
        SELECT
            1
        FROM
            RESTAURANT R
        WHERE
            R.CIF = TEMP.CIF
    );

-- Eliminar la tabla temporal si ya no es necesaria
DROP TABLE TEMP_RESTAURANT;