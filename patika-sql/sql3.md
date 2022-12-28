
+ country tablosunda bulunan country sütunundaki ülke isimlerinden 'A' karakteri ile başlayıp 
'a' karakteri ile sonlananları sıralayınız.
```sql
select * from country
where country like 'A%a';
```

+ country tablosunda bulunan country sütunundaki ülke isimlerinden en az 6 karakterden oluşan 
ve sonu 'n' karakteri ile sonlananları sıralayınız.
```sql
select * from country
where length(country)>= 6 and country like '%n';
```

+ film tablosunda bulunan title sütunundaki film isimlerinden en az 4 adet büyük ya da küçük harf farketmesizin 
'T' karakteri içeren film isimlerini sıralayınız.
```sql
select * from film
where title ilike '%t%t%t%t%';
```

+ film tablosunda bulunan tüm sütunlardaki verilerden title 'C' karakteri ile başlayan 
ve uzunluğu (length) 90 dan büyük olan ve rental_rate 2.99 olan verileri sıralayınız.
```sql
select * from film
where title like 'C%' and film.length > 90 and rental_rate = 2.99;
```
