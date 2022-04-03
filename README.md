#MovieLand
##Iteration v1:
- [x] [GET /v1/movie => Response should be in JSON format]()
- [X] [GET /v1/movie/random => get 3 random movies]()
- [x] [GET /v1/genre => get all genres]()
- [ ] [GET /v1/movie/genre/{genreId} => get movies by genre]()

####Configure file appender for logback.
1. All logs, starting from level DEBUG in your application, should be stored in file.
2. For file name, use next pattern:
   - current log: movieLand.log
   - previous logs: movieLand-[yyyy-MM-dd].log[.log_number], for example:
     - movieLand-2017-10-15.log.0 - for first log file which was published on 2017-10-15
3. Maximum size of log file is 5mb.

####Create genre cache
1. As list of genres is rather static, we can save time on DB calling. Instead of reading genres from DB, we will create genre cache to store them all.
2. In order to have relevant information, genre cache should be updated according to DB data each 4 hours.

- [ ] [GET /v1/movie?rating=desc]()
While sending request to get all movies [b-1] and get movies by genre [b-3], 
user should be able to sort requested movies by rating (desc) or price (acs/desc).
In order to do that, user should add parameter to request.

##Iteration v2:
- [ ] [GET /v1/movie/{movieId} => Get specific movie by its id, get movie details.]()
- [ ] [POST /v1/login => login]()
- [ ] [DELETE /v1/logout = > logout]()

####While sending requests to get movie by id, user should be able to add parameter with currency in order to receive movie price in selected currency.
1. In DB, all prices are stored in UAH.
2. Price can be converted to USD or EUR.
3. For example, /v1/movie/{movieId}?currency=USD.
4. Prices should be converted according to today NBU rate.
5. By default, selected currency is UAH.

####We need to have security service, which allows handling auth request with user login and password, and return generated identifier (token) to this user.
On server side we will link particular user with generated token (uuid), and in case of incoming request with this token, we will be able to recognize request's owner.
Each link between user and token should be stored in cache (use java, or any other collection) for 2 hours. After this time, it should be removed from cache.
Request header should contain valid "uuid" value.
In case of wrong combination of login\password send back 400 Bad request