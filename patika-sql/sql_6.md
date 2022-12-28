1. film tablosunda bulunan rental_rate sütunundaki değerlerin ortalaması nedir? 2.980
```sql
SELECT round(avg(rental_rate), 3) FROM film;
```

2. film tablosunda bulunan filmlerden kaç tanesi 'C' karakteri ile başlar? 92
```sql
SELECT count(*) from film
where title like 'C%';
```

3. film tablosunda bulunan filmlerden rental_rate değeri 0.99 a eşit olan en uzun (length) film kaç dakikadır? 184
```sql
select max(length) from film
where rental_rate = 0.99;
```

4. film tablosunda bulunan filmlerin uzunluğu 150 dakikadan büyük olanlarına ait kaç farklı 
replacement_cost değeri vardır? 21
```sql
Select count(distinct replacement_cost) from film
where length>150;
```
