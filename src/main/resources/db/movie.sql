create table movie (
                       movie_id SERIAL primary key,
                       name_translate VARCHAR (150) UNIQUE NOT NULL,
                       name_native VARCHAR (150) UNIQUE NOT NULL,
                       year_of_realise VARCHAR (150),
                       description VARCHAR (1024),
                       rating NUMERIC(3, 1),
                       price NUMERIC(6, 2),
                       picture_path VARCHAR (1024),
                       countries VARCHAR REFERENCES country(country_id),
                       genres VARCHAR REFERENCES genre(genre_id),
                       reviews VARCHAR REFERENCES review(review_id)
);