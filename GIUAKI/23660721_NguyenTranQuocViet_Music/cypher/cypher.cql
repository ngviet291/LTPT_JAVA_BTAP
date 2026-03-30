CREATE CONSTRAINT album
FOR (album:Album) REQUIRE album.id IS UNIQUE;

CREATE CONSTRAINT artist
FOR (artist:Artist) REQUIRE artist.id IS UNIQUE;

CREATE CONSTRAINT genre
FOR (genre:Genre) REQUIRE genre.id IS UNIQUE;

CREATE CONSTRAINT song
FOR (song:Song) REQUIRE song.id IS UNIQUE;

LOAD CSV WITH HEADERS FROM 'file:///album.csv' AS row
MERGE (album:Album {id:row.id,title: row.title, price: toFloat(row.price)
,yearOfRelease:row.yearOfRelease,downloadLink:row.downloadLink });

LOAD CSV WITH HEADERS FROM 'file:///artist.csv' AS row
MERGE (artist:Artist {id:row.id,name: row.name, birthDate: Date(row.birthDate), url: row.url});

LOAD CSV WITH HEADERS FROM 'file:///song.csv' AS row
MERGE (song:Song {id:row.id,name: row.name, runtime: row.runtime, lyric: row.lyric,fileLink: row.fileLink});

LOAD CSV WITH HEADERS FROM 'file:///genre.csv' AS row
MERGE (genre:Genre {id:row.id,name: row.name, description: row.description});

// Create relationships
LOAD CSV WITH HEADERS FROM 'file:///song.csv' AS row
MATCH (s:Song {id: row.id})
MATCH (alb:Album {id: row.albumId})
MERGE (alb)-[:HAVE]->(s);

// Create relationships
LOAD CSV WITH HEADERS FROM 'file:///album.csv' AS row
MATCH (g:Genre {id: row.genreId})
MATCH (alb:Album {id: row.id})
MERGE (alb)-[:BELONG_TO]->(g);

LOAD CSV WITH HEADERS FROM 'file:///album.csv' AS row
MATCH (a:Artist {id: row.artistId})
MATCH (alb:Album {id: row.id})
MERGE (alb)-[:BE_PERFORMED]->(a);
