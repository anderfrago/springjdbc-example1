
#Spring JDBC Datuetarako sarbidea

## Ikuspegi orokorra
Proiektu honetan, Springeko JDBC moduluaren erabilera praktikoko kasuak aztertuko ditugu. Spring JDBCko klaseak lau pakete ezberdinetan antolatzen dira:

 - core: Pakete honek JDBCren funtzionaltasun nagusia jasotzen du. Gako-klaseak honako hauek dira: `JdbcTemplate`, `SimpleJdbcInsert`, `SimpleJdbcCall` eta `NamedParameterJdbcTemplate`.
 - datasource: Pakete honek erabilgarritasun-motak ematen ditu datu-iturri batera sartzeko. JDBC kodea Java AEBetako edukiontzi batetik kanpo frogatzeko datu-iturrien hainbat inplementazio ere jasotzen ditu.
 - object: Pakete honek datu-baserako sarbidea errazten du, objektuei zuzenduta. Kontsultak egiteko eta emaitzak negozio-objektu gisa itzultzeko aukera ematen du, zutabeen eta negozio-objektuen propietateen arteko kontsulten emaitzak mapean.
 - support: Pakete honek euskarri-motak ditu core eta object paketeetarako, hala nola SQLException-en itzulpen-funtzionaltasuna ematen duten klaseak.

## Konfigurazioa
Hasteko, datu-iturriaren konfigurazio sinple batekin hasiko gara (adibide honetarako MySQL datu-base bat erabiliko dugu):

```java
@Configuration
@ComponentScan("org.cuatrovientos.jdbc")
public class SpringJdbcConfig {
    
        @Bean
        public DataSource mysqlDataSource() {
            
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/product-manager");
            dataSource.setUsername("root");
            dataSource.setPassword("");
            
            return dataSource;      
            
        }
}
```

Bestela, garapenerako edo probetarako txertatutako datu-base bat ere ondo erabil dezakegu: hemen konfigurazio azkar bat, H2 txertatuaren datu-basearen instantzia bat sortzen duena, eta aldez aurretik SQL scripts sinpleekin betegarria:

```java
@Bean
public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:jdbc/schema.sql")
        .addScript("classpath:jdbc/test-data.sql").build();
}
```

The JdbcTemplate and running queries
JdbcTemplate behar dugun funtzionalitate nagusia eskuratzeko API nagusia da, honako hauek barne hartzen dituena:
-	Konexioak sortzea eta ixtea
-	Biltegiratutako prozeduretarako jarraibideak eta deiak gauzatzea
-	'ResultSet'-ari buruz iteratzea eta emaitzak itzultzea
Adibide sinple batekin hasten gara, 'JdbcTemplate'-ren gaitasunak erakusteko:


```java
public List<Category> findById(Long id) {
   return jdbcTemplate.query("select * from category where id = ?",
       (rs, rowNum) -> new Category(rs.getLong("id"), rs.getString("name")), id);
}
```

eta hemen ere bada INSERT sinple bat:

```java
public int save(Category category) {
return jdbcTemplate.update("INSERT INTO category (id, name) values(?, ?)",  category.getId(), category.getName());


```

Begiratu parametroak emateko sintaxi estandarra, '?' izaera erabiliz.


