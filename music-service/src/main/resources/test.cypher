//Create Users
CREATE (user: User {id: 'user1',name:'User 1',dob:'1990-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (user1: User {id: 'user2',name:'User 2',dob:'1996-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (user2: User {id: 'user3',name:'User 3',dob:'2003-01-23',gender:'FEMALE',language:'en',countryIso2:'ZW'})
CREATE (user3: User {id: 'user4',name:'User 4',dob:'2023-01-23',gender:'FEMALE',language:'en',countryIso2:'BE'})
CREATE (user4: User {id: 'user5',name:'User 5',dob:'1987-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (user5: User {id: 'user6',name:'User 6',dob:'1987-12-23',gender:'MALE',language:'en',countryIso2:'FR'})

//Create Artists
CREATE (artist1: Artist {id: 'artist1',name:'Artist 1',dob:'1990-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (artist2: Artist {id: 'artist2',name:'Artist 2',dob:'1996-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (artist3: Artist {id: 'artist3',name:'Artist 3',dob:'2003-01-23',gender:'FEMALE',language:'en',countryIso2:'ZW'})
CREATE (artist4: Artist {id: 'artist4',name:'Artist 4',dob:'2023-01-23',gender:'FEMALE',language:'en',countryIso2:'BE'})
CREATE (artist5: Artist {id: 'artist5',name:'Artist 5',dob:'1987-01-23',gender:'MALE',language:'en',countryIso2:'ZW'})
CREATE (artist6: Artist {id: 'artist6',name:'Artist 6',dob:'1987-12-23',gender:'MALE',language:'en',countryIso2:'FR'})

// Add Artist and User Relationship
MATCH (user6:User {id: 'user1'}), (artist7:Artist {id: 'artist1'}) MERGE (user6)-[:IS_AN]->(artist7)
MATCH (user7:User {id: 'user2'}), (artist8:Artist {id: 'artist2'}) MERGE (user7)-[:IS_AN]->(artist8)
MATCH (user8:User {id: 'user3'}), (artist9:Artist {id: 'artist3'}) MERGE (user8)-[:IS_AN]->(artist9)
MATCH (user9:User {id: 'user4'}), (artist10:Artist {id: 'artist4'}) MERGE (user9)-[:IS_AN]->(artist10)
MATCH (user10:User {id: 'user5'}), (artist11:Artist {id: 'artist5'}) MERGE (user10)-[:IS_AN]->(artist11)
MATCH (user11:User {id: 'user6'}), (artist12:Artist {id: 'artist6'}) MERGE (user11)-[:IS_AN]->(artist12)

// create year
CREATE (year: Year {year: 2022})
CREATE (year: Year {year: 2021})
CREATE (year: Year {year: 2023})
CREATE (year: Year {year: 2020})
CREATE (year: Year {year: 2019})
CREATE (year: Year {year: 2018})
CREATE (year: Year {year: 2017})

// Create genres
CREATE (g1: Genre {id: 'hip_hop', name:'Hip Hop'})
CREATE (g2: Genre {id: 'dancehall', name:'Dancehall'})
CREATE (g3: Genre {id: 'pop', name:'Pop'})


// Add Artist To genre
MATCH (a:Artist {id: 'artist1'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)
MATCH (a:Artist {id: 'artist2'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)
MATCH (a:Artist {id: 'artist3'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)
MATCH (a:Artist {id: 'artist4'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)
MATCH (a:Artist {id: 'artist5'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)
MATCH (a:Artist {id: 'artist6'}), (g:Genre {id: 'dancehall'})
MERGE (a)-[:BELONGS_TO_GENRE]->(g)


// Artist Create Album
CREATE (album: Album {id: 'album1',name:'album 1',description:'description',status:'DRAFT'})
CREATE (album: Album {id: 'album2',name:'album 2',description:'description',status:'DRAFT'})
CREATE (album: Album {id: 'album3',name:'album 3',description:'description',status:'DRAFT'})
CREATE (album: Album {id: 'album4',name:'album 4',description:'description',status:'DRAFT'})
CREATE (album: Album {id: 'album5',name:'album 5',description:'description',status:'DRAFT'})
CREATE (album: Album {id: 'album6',name:'album 6',description:'description',status:'DRAFT'})

// Add album
MATCH (album:Album {id: 'album1'})
MATCH (artist:Artist {id: 'artist1'})
MATCH (year:Year {year: 2023})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)
MATCH (album:Album {id: 'album2'})
MATCH (artist:Artist {id: 'artist2'})
MATCH (year:Year {year: 2022})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)
MATCH (album:Album {id: 'album3'})
MATCH (artist:Artist {id: 'artist3'})
MATCH (year:Year {year: 2021})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)
MATCH (album:Album {id: 'album4'})
MATCH (artist:Artist {id: 'artist4'})
MATCH (year:Year {year: 2020})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)
MATCH (album:Album {id: 'album5'})
MATCH (artist:Artist {id: 'artist5'})
MATCH (year:Year {year: 2020})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)
MATCH (album:Album {id: 'album6'})
MATCH (artist:Artist {id: 'artist6'})
MATCH (year:Year {year: 2019})
CREATE (artist)-[:CREATED {createdAt: localdatetime()}]->(album)
MERGE (album)-[:RELEASED_IN]->(year)

// create songs
CREATE (song: Song {id: 'song1',name:'song 1',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song2',name:'song 2',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song3',name:'song 3',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song4',name:'song 4',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song5',name:'song 5',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song6',name:'song 6',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song7',name:'song 7',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song8',name:'song 8',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song9',name:'song 9',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song10',name:'song 10',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song11',name:'song 11',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song12',name:'song 12',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song13',name:'song 13',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song14',name:'song 14',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})
CREATE (song: Song {id: 'song15',name:'song 15',description:'description',status:'DRAFT',duration: 320,storageId:'hhjd74hjhhsd',storageType:'BUNNY_NET',type:'AUDIO'})


// Add songs to genre

MATCH (song:Song {id: 'song1'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song2'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song3'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song4'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song5'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song6'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song7'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song8'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song9'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song10'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song11'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song12'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song13'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song14'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)
MATCH (song:Song {id: 'song15'}), (g:Genre {id: 'dancehall'})
MERGE (song)-[:BELONGS_TO_GENRE]->(g)


// Add song and artist relationship
MATCH (song:Song {id: 'song1'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist1'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song2'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist2'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song3'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist3'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song4'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist4'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song5'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist5'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song6'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist6'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song7'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist1'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song8'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist1'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song9'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist2'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song10'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist2'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song11'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist3'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song12'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist5'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song13'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist4'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song14'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist6'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)
MATCH (song:Song {id: 'song15'}), (year:Year {year: 2023}) , (artist:Artist {id: 'artist3'})
MERGE (artist)-[:CREATED {createdAt: localdatetime()}]->(song)
MERGE (song)-[:RELEASED_IN]->(year)

//Add song and album relationship
MATCH (song:Song {id: 'song1'})
MATCH (album:Album {id: 'album1'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song2'})
MATCH (album:Album {id: 'album2'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song3'})
MATCH (album:Album {id: 'album3'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song4'})
MATCH (album:Album {id: 'album4'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song5'})
MATCH (album:Album {id: 'album5'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song6'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song7'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song8'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song9'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song10'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song11'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)
MATCH (song:Song {id: 'song12'})
MATCH (album:Album {id: 'album6'})
CREATE (album)<-[:IS_IN {createdAt: localdatetime()}]-(song)

//User likes an album
MATCH (album:Album {id: 'album1'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)
MATCH (album:Album {id: 'album1'})
MATCH (user:User {id: 'user2'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)
MATCH (album:Album {id: 'album1'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)
MATCH (album:Album {id: 'album2'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)
MATCH (album:Album {id: 'album3'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)
MATCH (album:Album {id: 'album3'})
MATCH (user:User {id: 'user5'})
CREATE (user)-[:LIKES {createdAt: localdatetime()}]->(album)

//Users follows artists
MATCH (artist:Artist {id: 'artist1'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist1'})
MATCH (user:User {id: 'user2'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist2'})
MATCH (user:User {id: 'user2'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist3'})
MATCH (user:User {id: 'user2'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist3'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist4'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist2'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)
MATCH (artist:Artist {id: 'artist1'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)

MATCH (artist:Artist {id: 'artist2'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:FOLLOWS {createdAt: localdatetime()}]->(artist)


//Users likes songs
MATCH (song:Song {id: 'song1'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song2'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song3'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song4'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song5'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song5'})
MATCH (user:User {id: 'user2'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song5'})
MATCH (user:User {id: 'user3'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)
MATCH (song:Song {id: 'song5'})
MATCH (user:User {id: 'user1'})
CREATE (user)-[:LIKES {createdAt:  localdatetime()}]->(song)

MATCH (u:User {id: 'user1'})
MATCH (s:Song {id: 'song1'})
CREATE (c:Comment {id: 'comment1',text: 'Nice song', createdAt: localdatetime()})
MERGE (u)-[:POSTED_COMMENT]->(c)
MERGE (c)-[:HAS_COMMENT]->(s)
RETURN c


MATCH (u:User {id: 'user2'})
MATCH (s:Song {id: 'song1'})
CREATE (c:Comment {id: 'comment2',text: 'Nice song boss', createdAt: localdatetime()})
MERGE (u)-[:POSTED_COMMENT]->(c)
MERGE (c)-[:HAS_COMMENT]->(s)
RETURN c

MATCH (u:User {id: 'user3'})
MATCH (s:Song {id: 'song2'})
CREATE (c:Comment {id: 'comment3',text: 'Love it', createdAt: localdatetime()})
MERGE (u)-[:POSTED_COMMENT]->(c)
MERGE (c)-[:HAS_COMMENT]->(s)
RETURN c

MATCH (u:User {id: 'user3'})
MATCH (s:Song {id: 'song2'})
CREATE (c:Comment {id: 'comment4',text: 'You nailed it', createdAt: localdatetime()})
MERGE (u)-[:POSTED_COMMENT]->(c)
MERGE (c)-[:HAS_COMMENT]->(s)
RETURN c

// Add featuring artist
MATCH (a:Artist {id: 'artist1'})
MATCH (s:Song {id: 'song2'})
MERGE (s)<-[:FEATURED_WITH]-(a)
MATCH (a:Artist {id: 'artist1'})
MATCH (s:Song {id: 'song3'})
MERGE (s)<-[:FEATURED_WITH]-(a)
MATCH (a:Artist {id: 'artist4'})
MATCH (s:Song {id: 'song5'})
MERGE (s)<-[:FEATURED_WITH]-(a)
MATCH (a:Artist {id: 'artist4'})
MATCH (s:Song {id: 'song3'})
MERGE (s)<-[:FEATURED_WITH]-(a)

// Create user playlist
MATCH (u:User {id: 'user1'})
CREATE (p:Playlist {id: 'playlist1', title: 'Playlist 1', description: 'my lovely playlist1'})
CREATE (u)-[:CREATED]->(p)
RETURN p
MATCH (u:User {id: 'user2'})
CREATE (p:Playlist {id: 'playlist2', title: 'Playlist 2', description: 'my lovely playlist2'})
CREATE (u)-[:CREATED]->(p)
RETURN p
MATCH (u:User {id: 'user3'})
CREATE (p:Playlist {id: 'playlist3', title: 'Playlist 3', description: 'my lovely playlist3'})
CREATE (u)-[:CREATED]->(p)
RETURN p
MATCH (u:User {id: 'user4'})
CREATE (p:Playlist {id: 'playlist4', title: 'Playlist 4', description: 'my lovely playlist4'})
CREATE (u)-[:CREATED]->(p)
RETURN p
MATCH (u:User {id: 'user5'})
CREATE (p:Playlist {id: 'playlist5', title: 'Playlist 5', description: 'my lovely playlist5'})
CREATE (u)-[:CREATED]->(p)
RETURN p


// add songs into playlist
MATCH (playlist:Playlist {id: 'playlist1'})
MATCH (song:Song {id: 'song1'})
MERGE (playlist)-[:CONTAINS]->(song)
MATCH (playlist:Playlist {id: 'playlist1'})
MATCH (song:Song {id: 'song2'})
MERGE (playlist)-[:CONTAINS]->(song)
MATCH (playlist:Playlist {id: 'playlist1'})
MATCH (song:Song {id: 'song3'})
MERGE (playlist)-[:CONTAINS]->(song)
MATCH (playlist:Playlist {id: 'playlist1'})
MATCH (song:Song {id: 'song7'})
MERGE (playlist)-[:CONTAINS]->(song)
MATCH (playlist:Playlist {id: 'playlist1'})
MATCH (song:Song {id: 'song12'})
MERGE (playlist)-[:CONTAINS]->(song)


// First Recommendation
// Step 1 get artist followers
MATCH (user:User)-[f:FOLLOWS]->(artist:Artist)
RETURN artist.name AS name, COUNT(DISTINCT user) AS artist_followers
  ORDER BY artist_followers DESC

/*
In summary, this Cypher query is designed to identify users who follow the same artists as the specified user ('user1'),
 find other users who also follow those artists, and recommend songs created by those artists that the original user is not already following.
  It's a collaborative filtering approach that leverages the preferences of similar users to generate personalized music recommendations for the target user.
*/
MATCH (user:User {id:'user1'})-[follows:FOLLOWS]->(artist:Artist)<-[otherFollowers:FOLLOWS]-(otherUser: User)-[followsArtist:FOLLOWS]->(followArtist:Artist)
MATCH (followArtist)-[createdSongRelationship:CREATED]->(song:Song)
  WHERE NOT (user)-[:FOLLOWS ]-> (followArtist)
RETURN user, follows, artist, otherFollowers,otherUser,followsArtist,followArtist,createdSongRelationship,song


//modified query which limits single artist to have 3 songs
MATCH (user:User {id:'user1'})-[follows:FOLLOWS]->(artist:Artist)<-[otherFollowers:FOLLOWS]-(otherUser:User)
MATCH (otherUser)-[followsArtist:FOLLOWS]->(followArtist:Artist)-[createdSongRelationship:CREATED]->(song:Song)
  WHERE NOT (user)-[:FOLLOWS]->(followArtist) // AND NOT song.status = 'DRAFT' // update status to PUBLISHED on test environment
WITH followArtist, COUNT(DISTINCT otherUser) AS artistFollowers, COLLECT(DISTINCT song) AS recommendedSongs
WITH followArtist, artistFollowers, recommendedSongs[0..3] AS limitedSongs
RETURN followArtist, artistFollowers, limitedSongs
  ORDER BY artistFollowers DESC
  SKIP 0
  LIMIT 5;
